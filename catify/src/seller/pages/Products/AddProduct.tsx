import { AddPhotoAlternate, Category, Close } from "@mui/icons-material";
import {
  Button,
  CircularProgress,
  FormControl,
  FormHelperText,
  Grid,
  IconButton,
  InputLabel,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";
import { useFormik } from "formik";
import React, { useState } from "react";
import { uploadToCloudnary } from "../../../Utils/UploadToCloudnary";
import { colors } from "../../../data/filter/color";
import { mainCategory } from "../../../data/category/mainCategory";

const AddProduct = () => {
  const [uploadImage, setUploadingImage] = useState(false);
  const [snackbarOpen, setOpenSnakebar] = useState(false);
  const formik = useFormik({
    initialValues: {
      title: "",
      discription: "",
      mrpPrice: "",
      sellingPrice: "",
      quantity: "",
      color: "",
      images: [],
      category: "",
      category1: "",
      category2: "",
      category3: "",
      sizes: "",
    },
    onSubmit: (value) => {
      console.log(value);
    },
  });
  const sizeOptions = [
    { categoryId: "S", name: "Small" },
    { categoryId: "M", name: "Medium" },
    { categoryId: "L", name: "Large" },
  ];

  const handleImageChange = async (event: any) => {
    const file = event.target.files[0];
    setUploadingImage(true);
    const image = await uploadToCloudnary(file);
    formik.setFieldValue("image", [...formik.values.images, image]);
    setUploadingImage(false);
    event.target.value = "";
  };
  const handleRemoveImage = (index: number) => {
    const updateImages = [...formik.values.images];
    updateImages.splice(index, 1);
    formik.setFieldValue("images", updateImages);
  };
  const childCategory = (Category: any, parentCategoryId: any) => {
    return Category.filter((child: any) => {
      return child.parentCategoryId == parentCategoryId;
    });
  };
  const handleCloseSnackbar = () => {
    setOpenSnakebar(false);
  };
  return (
    <div>
      <form action="">
        <Grid container spacing={2}>
          <Grid className="flex flex-wrap gap-5" size={{ xs: 12 }}>
            <input
              type="file"
              accept="image/*"
              id="fileInput"
              style={{ display: "none" }}
              onChange={handleImageChange}
            />

            <label className="relative" htmlFor="fileInput">
              <span className="w-24 h-24 cursor-pointer flex items-center justify-center p-3 border rounded-md border-gray-400">
                <AddPhotoAlternate className="text-gray-700" />
              </span>
              {uploadImage && (
                <div className="absolute left-0 top-0 bottom-0 w-24 h-24 flex justify-center items-center">
                  <CircularProgress />
                </div>
              )}
            </label>

            <div className="flex flex-wrap gap-2">
              {formik.values.images.map((image, index) => (
                <div className="relative">
                  <img
                    className="w-24 h-24 object-cover"
                    key={image}
                    src={image}
                    alt={`ProductImage${index + 1}`}
                  />
                  <IconButton
                    onClick={() => handleRemoveImage(index)}
                    className=""
                    size="small"
                    color="error"
                    sx={{
                      position: "absolute",
                      top: 0,
                      right: 0,
                      outline: "none",
                    }}
                  >
                    <Close sx={{ fontSize: "1rem" }} />
                  </IconButton>
                </div>
              ))}
            </div>
          </Grid>
          <Grid size={{ xs: 12 }}>
            <TextField
              fullWidth
              id="title"
              name="title"
              label="Title"
              value={formik.values.title}
              onChange={formik.handleChange}
              error={formik.touched.title && Boolean(formik.errors.title)}
              helperText={formik.touched.title && formik.errors.title}
              required
            />
          </Grid>
          <Grid size={{ xs: 12 }}>
            <TextField
              fullWidth
              multiline
              rows={4}
              id="description"
              name="description"
              label="Description"
              type="text"
              value={formik.values.discription}
              onChange={formik.handleChange}
              error={
                formik.touched.discription && Boolean(formik.errors.discription)
              }
              helperText={
                formik.touched.discription && formik.errors.discription
              }
              required
            />
          </Grid>

          <Grid size={{ xs: 12, md: 4, lg: 3 }}>
            <TextField
              fullWidth
              id="mrpPrice"
              name="mrpPrice"
              label="MRP Price"
              type="number"
              value={formik.values.mrpPrice}
              onChange={formik.handleChange}
              error={formik.touched.mrpPrice && Boolean(formik.errors.mrpPrice)}
              helperText={formik.touched.mrpPrice && formik.errors.mrpPrice}
              required
            />
          </Grid>
          <Grid size={{ xs: 12, md: 4, lg: 3 }}>
            <TextField
              fullWidth
              id="selllingPrice"
              name="sellingPrice"
              label="Selling Price"
              type="number"
              value={formik.values.sellingPrice}
              onChange={formik.handleChange}
              error={
                formik.touched.sellingPrice &&
                Boolean(formik.errors.sellingPrice)
              }
              helperText={
                formik.touched.sellingPrice && formik.errors.sellingPrice
              }
              required
            />
          </Grid>
          <Grid size={{ xs: 12, md: 4, lg: 3 }}>
            <FormControl
              fullWidth
              error={formik.touched.color && Boolean(formik.errors.color)}
              required
            >
              <InputLabel id="color-label">Color</InputLabel>
              <Select
                labelId="color-label"
                name="color"
                id="color"
                onChange={formik.handleChange}
                value={formik.values.color}
                label="Color"
              >
                <MenuItem value="">
                  <em>None</em>
                </MenuItem>
                {colors.map((color, index) => (
                  <MenuItem value={color.name}>
                    <div className="flex gap-3">
                      <span
                        style={{ backgroundColor: color.hex }}
                        className={`h-5 w-5 rounded-full ${
                          color.name === "white" ? "border" : ""
                        }`}
                      >
                        <p>{color.name}</p>
                      </span>
                    </div>
                  </MenuItem>
                ))}
              </Select>
              {formik.touched.color && formik.errors.color && (
                <FormHelperText>{formik.errors.color}</FormHelperText>
              )}
            </FormControl>
          </Grid>
          <Grid size={{ xs: 12, md: 4, lg: 3 }}>
            <FormControl
              fullWidth
              error={formik.touched.sizes && Boolean(formik.errors.sizes)}
              required
            >
              <InputLabel id="size-label">Size</InputLabel>
              <Select
                labelId="size-label"
                name="sizes"
                id="sizes"
                onChange={formik.handleChange}
                value={formik.values.sizes}
                label="Size"
              >
                <MenuItem value="">
                  <em>None</em>
                </MenuItem>

                {sizeOptions.map((item) => (
                  <MenuItem key={item.categoryId} value={item.categoryId}>
                    {item.name}
                  </MenuItem>
                ))}
              </Select>

              {formik.touched.sizes && formik.errors.sizes && (
                <FormHelperText>{formik.errors.sizes}</FormHelperText>
              )}
            </FormControl>
          </Grid>

          <Grid size={{ xs: 12, md: 4, lg: 4 }}>
            <FormControl
              fullWidth
              error={formik.touched.category && Boolean(formik.errors.category)}
              required
            >
              <InputLabel id="category-label">Category</InputLabel>
              <Select
                labelId="category-label"
                name="category"
                id="category"
                onChange={formik.handleChange}
                value={formik.values.category}
                label="Category"
              >
                {mainCategory.map((item) => (
                  <MenuItem value={item.categoryId}>{item.name}</MenuItem>
                ))}
              </Select>
              {formik.touched.color && formik.errors.color && (
                <FormHelperText>{formik.errors.color}</FormHelperText>
              )}
            </FormControl>
          </Grid>
          <Grid size={{ xs: 12, md: 4, lg: 4 }}>
            <FormControl
              fullWidth
              error={formik.touched.category && Boolean(formik.errors.category)}
              required
            >
              <InputLabel id="category-label">Second Category</InputLabel>
              <Select
                labelId="category2-label"
                name="category3"
                id="category"
                onChange={formik.handleChange}
                value={formik.values.category}
                label="Category"
              >
                {mainCategory.map((item) => (
                  <MenuItem value={item.categoryId}>{item.name}</MenuItem>
                ))}
              </Select>
              {formik.touched.color && formik.errors.color && (
                <FormHelperText>{formik.errors.color}</FormHelperText>
              )}
            </FormControl>
          </Grid>

          <Grid size={{ xs: 12, md: 4, lg: 4 }}>
            <FormControl
              fullWidth
              error={formik.touched.category && Boolean(formik.errors.category)}
              required
            >
              <InputLabel id="category-label">Third Category</InputLabel>
              <Select
                labelId="category3-label"
                name="category3"
                id="category"
                onChange={formik.handleChange}
                value={formik.values.category}
                label="Category"
              >
                {mainCategory.map((item) => (
                  <MenuItem value={item.categoryId}>{item.name}</MenuItem>
                ))}
              </Select>
              {formik.touched.color && formik.errors.color && (
                <FormHelperText>{formik.errors.color}</FormHelperText>
              )}
            </FormControl>
          </Grid>
          <Grid size={{xs:12}}>
            <Button
              variant="contained"
              sx={{ p: "14px"}}
              color="primary"
              fullWidth
              type="submit"
            >
              {false ? <CircularProgress size={24} /> : "Add Product"}
            </Button>
          </Grid>
        </Grid>
      </form>
    </div>
  );
};

export default AddProduct;
