import React, { useState } from "react";
import {
  Button,
  Divider,
  FormControl,
  FormControlLabel,
  FormLabel,
  Pagination,
  Radio,
  RadioGroup,
} from "@mui/material";
import { teal } from "@mui/material/colors";
import { useSearchParams } from "react-router-dom";

import { colors } from "../../../data/filter/color";
import { price } from "../../../data/filter/price";
import { discount } from "../../../data/filter/discount";

const FilterSection = () => {
  const [expandColor, setExpandColor] = useState(false);
  const [searchParams, setSearchParams] = useSearchParams();

  const handleColorToggle = () => {
    setExpandColor(!expandColor);
  };

  const updateFilterParams = (e: any) => {
    const { value, name } = e.target;

    if (value) {
      searchParams.set(name, value);
    } else {
      searchParams.delete(name);
    }

    setSearchParams(searchParams);
  };

  const clearAllFilters = () => {
    searchParams.forEach((_: any, key: any) => {
      searchParams.delete(key);
    });
    setSearchParams(searchParams);
  };

  return (
    <div className="-z-50 space-y-5 bg-white">
      {/* Header */}
      <div className="flex items-center justify-between h-[40px] px-9 lg:border-r">
        <p className="text-lg font-semibold">Filters</p>
        <Button
          onClick={clearAllFilters}
          size="small"
          className="text-primary-color cursor-pointer font-semibold"
        >
          Clear All
        </Button>
      </div>

      <Divider />

      <div className="px-9 space-y-6">
        {/* Color Filter */}
        <section>
          <FormControl>
            <FormLabel
              id="color"
              sx={{
                fontSize: "16px",
                fontWeight: "bold",
                color: teal[500],
                pb: "14px",
              }}
            >
              Color
            </FormLabel>

            <RadioGroup aria-labelledby="color" name="color" onChange={updateFilterParams}>
              {colors
                .slice(0, expandColor ? colors.length : 5)
                .map((item, index) => (
                  <FormControlLabel
                    key={index}
                    value={item.hex || item.name}
                    control={<Radio size="small" />}
                    label={
                      <div className="flex items-center gap-3">
                        <p>{item.name}</p>
                        <div
                          style={{ backgroundColor: item.hex }}
                          className={`h-5 w-5 rounded-full ${
                            item.name === "white" ? "border" : ""
                          }`}
                        ></div>
                      </div>
                    }
                    onChange={updateFilterParams}
                  />
                ))}
            </RadioGroup>
          </FormControl>

          <Button
            onClick={handleColorToggle}
            className="text-primary-color cursor-pointer hover:text-teal-900 flex items-center"
          >
            {expandColor ? "Hide" : `+${colors.length - 5} more`}
          </Button>
        </section>

        {/* Price Filter */}
        <section>
          <FormControl>
            <FormLabel
              sx={{
                fontSize: "16px",
                fontWeight: "bold",
                color: teal[600],
                pb: "14px",
              }}
            >
              Price
            </FormLabel>

            <RadioGroup
              aria-labelledby="Price"
              name="Price"
              onChange={updateFilterParams}
            >
              {price.map((item, index) => (
                <FormControlLabel
                  key={index}
                  value={item.value}
                  control={<Radio size="small" />}
                  label={item.name}
                />
              ))}
            </RadioGroup>
          </FormControl>
        </section>

        {/* Discount Filter */}
        <section>
          <FormControl>
            <FormLabel
              id="discount"
              sx={{
                fontSize: "16px",
                fontWeight: "bold",
                color: teal[600],
                pb: "14px",
              }}
            >
              Discount
            </FormLabel>

            <RadioGroup
              aria-labelledby="Discount"
              name="Discount"
              onChange={updateFilterParams}
            >
              {discount.map((item, index) => (
                <FormControlLabel
                  key={index}
                  value={item.value}
                  control={<Radio size="small" />}
                  label={item.name}
                />
              ))}
            </RadioGroup>
          </FormControl>
        </section>
      </div>
    </div>
  );
};

export default FilterSection;
