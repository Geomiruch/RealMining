package com.realmining.realminingmod.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.realmining.realminingmod.RealMiningMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Special shapeless recipe that allows use of Tools, and damages the tool properly
 *
 * @author Credit to Integral for the Enchantment Transpose Recipe as a guide
 */

public class DamageItemRecipe extends ShapelessRecipe {
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    private DamageItemRecipe(ResourceLocation id, String group, ItemStack output,
                             NonNullList<Ingredient> inputs) {
        super(id, group, output, inputs);
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    @Nonnull
    public ItemStack assemble(CraftingInventory inv) {
        return this.output.copy();
    }

    @Override
    public boolean matches(CraftingInventory inv, World world) {
        RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
        int i = 0;

        for (int j = 0; j < inv.getContainerSize(); ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                recipeitemhelper.accountStack(itemstack, 1);
            }
        }

        return i == this.inputs.size() && recipeitemhelper.canCraft(this, null);
    }

    @Override
    @Nonnull
    public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
        NonNullList<ItemStack> nonnulllist =
                NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack stack = inv.getItem(i);
            if (stack.isEmpty()) {
                continue;
            }
            if (stack.getItem().isDamageable(stack)) {
                ItemStack savedStack = stack.copy();
                boolean shouldAttemptDmg = true;
                Random random = new Random();
                int unbreakingLvl =
                        EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, savedStack);

                if (unbreakingLvl > 0) {
                    shouldAttemptDmg = (1 + random.nextInt(5)) <= unbreakingLvl;
                }

                if (savedStack.getDamageValue() < savedStack.getMaxDamage()) {
                    if (shouldAttemptDmg) {
                        savedStack.setDamageValue(savedStack.getDamageValue() + 1);
                    }
                    nonnulllist.set(i, savedStack);
                } else {
                    nonnulllist.set(i, ItemStack.EMPTY);
                }
            }
        }

        return nonnulllist;
    }

    @Override
    @Nonnull
    public IRecipeSerializer<?> getSerializer() {
        return RealMiningMod.DAMAGE_ITEM_RECIPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<DamageItemRecipe> {
        @Override
        @Nonnull
        public DamageItemRecipe fromJson(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json) {
            String s = JSONUtils.getAsString(json, "group", "");
            NonNullList<Ingredient> nonnulllist =
                    readIngredients(JSONUtils.getAsJsonArray(json, "ingredients"));

            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for natural-progression:damage_tools");
            }

            ItemStack stack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
            return new DamageItemRecipe(recipeId, s, stack, nonnulllist);
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray jsonArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for (int i = 0; i < jsonArray.size(); i++) {
                Ingredient ingr = Ingredient.fromJson(jsonArray.get(i));
                if (!ingr.isEmpty()) {
                    nonnulllist.add(ingr);
                }
            }
            return nonnulllist;
        }

        @Override
        public DamageItemRecipe fromNetwork(@Nonnull ResourceLocation recipeId,
                                     @Nonnull PacketBuffer buffer) {
            String s = buffer.readUtf(32767);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

            for (int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.fromNetwork(buffer));
            }

            ItemStack stack = buffer.readItem();
            return new DamageItemRecipe(recipeId, s, stack, nonnulllist);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, DamageItemRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeVarInt(recipe.inputs.size());

            for (Ingredient ingredient : recipe.inputs) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.output);
        }
    }
}