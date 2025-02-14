package com.unlikepaladin.pfm.compat.fabric.rei;
/*
 * This file is licensed under the MIT License, part of Roughly Enough Items.
 * Copyright (c) 2018, 2019, 2020, 2021, 2022 shedaniel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.unlikepaladin.pfm.recipes.FurnitureRecipe;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;

public class ShapedDisplay extends FurnitureDisplay<FurnitureRecipe> {
    public ShapedDisplay(FurnitureRecipe recipe) {
        super(
                EntryIngredients.ofIngredients(recipe.getIngredients()),
                Collections.singletonList(EntryIngredients.of(recipe.getOutput())),
                java.util.Optional.of(recipe)
        );
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public int getWidth() {
        return recipe.get().getWidth();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public int getHeight() {
        return recipe.get().getHeight();
    }
}