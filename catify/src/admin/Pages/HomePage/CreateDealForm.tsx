import {
  Box,
  Button,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  TextField,
  Typography,
} from "@mui/material";
import { useFormik } from "formik";
import React from "react";

const CreateDealForm = () => {
  const formik = useFormik({
    initialValues: {
      discount: 0,
      category: "",
    },
    onSubmit: (value) => {
      console.log(value);
    },
  });
  return (
    <Box
      component={"form"}
      onSubmit={formik.handleChange}
      className="space-y-6"
    >
      <Typography variant="h4" className="text-center">
        Create Deal
      </Typography>
      <TextField
        fullWidth
        name="discount"
        label="Discount"
        value={formik.values.discount}
        onChange={formik.handleChange}
        error={formik.touched.discount && Boolean(formik.errors.discount)}
        helperText={formik.touched.discount && formik.errors.discount}
        required
      />

      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Category</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={formik.values.category}
          label="Category"
          onChange={formik.handleChange}
        >
          <MenuItem value={10}>Ten</MenuItem>
          <MenuItem value={20}>Twenty</MenuItem>
          <MenuItem value={30}>Thirty</MenuItem>
        </Select>
      </FormControl>
      <Button sx={{py:".9rem"}} fullWidth type="submit" variant="contained">
        Create Deal
      </Button>
    </Box>
  );
};

export default CreateDealForm;
