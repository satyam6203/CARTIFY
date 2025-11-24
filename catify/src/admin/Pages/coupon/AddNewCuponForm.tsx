import { LocalizationProvider } from "@mui/x-date-pickers";
import { Dayjs } from "dayjs";
import { useFormik } from "formik";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import React from "react";
import { Box, Button, Grid, TextField } from "@mui/material";

interface CouponFormValues {
  code: string;
  discountPrecentage: number;
  validityStartDate: Dayjs | null;
  validityEndDate: Dayjs | null;
  minimumOrderValue: number;
}
const AddNewCuponForm = () => {
  const formik = useFormik<CouponFormValues>({
    initialValues: {
      code: "",
      discountPrecentage: 0,
      validityStartDate: null,
      validityEndDate: null,
      minimumOrderValue: 0,
    },
    onSubmit: (values) => {
      console.log(values);

      const formattedValues = {
        ...values,
        validityStartDate: values.validityStartDate
          ? values.validityStartDate.toISOString()
          : null,
        validityEndDate: values.validityEndDate
          ? values.validityEndDate.toISOString()
          : null,
      };

      console.log(formattedValues);
    },
  });
  return (
    <div>
      <h1 className="text-2xl font-bold text-primary-color pb-5 text-center">Create New Coupon</h1>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <Box component={"form"} onSubmit={formik.handleSubmit} sx={{ mt: 3 }}>
          <Grid container spacing={2}>
            <Grid size={{ xs: 12 ,sm:6}}>
              <TextField
                fullWidth
                label="coupon code"
                name="code"
                type="text"
                value={formik.values.code}
                onChange={formik.handleChange}
                error={
                  formik.touched.code &&
                  Boolean(formik.errors.code)
                }
                helperText={
                  formik.touched.code && formik.errors.code
                }
                required
              />
            </Grid>
            <Grid size={{ xs: 12 ,sm:6}}>
              <TextField
                fullWidth
                label="Discount Percent"
                name="discount"
                type="text"
                value={formik.values.discountPrecentage}
                onChange={formik.handleChange}
                error={
                  formik.touched.discountPrecentage &&
                  Boolean(formik.errors.discountPrecentage)
                }
                helperText={
                  formik.touched.discountPrecentage && formik.errors.discountPrecentage
                }
                required
              />
            </Grid>
            <Grid size={{ xs: 12 ,sm:6}}>
              <DatePicker 
                sx={{width:"100%"}}
                label="Validity Start Date"
                onChange={formik.handleChange}
                value={formik.values.validityStartDate}
                name="ValidityStartDate"
              />
            </Grid>
            <Grid size={{ xs: 12 ,sm:6}}>
              <DatePicker 
                sx={{width:"100%"}}
                label="Validity End Date"
                onChange={formik.handleChange}
                value={formik.values.validityEndDate}
                name="validityEndDate"
              />
            </Grid>
            <Grid size={{ xs: 12}}>
              <TextField
                fullWidth
                label="Minimum Order Value"
                name="OrderValue"
                type="number"
                value={formik.values.discountPrecentage}
                onChange={formik.handleChange}
                error={
                  formik.touched.discountPrecentage &&
                  Boolean(formik.errors.discountPrecentage)
                }
                helperText={
                  formik.touched.discountPrecentage && formik.errors.minimumOrderValue
                }
                required
              />
            </Grid>
            <Grid size={{xs:12}}>
              <Button variant="contained" fullWidth sx={{py:".8rem"}}>
                Create Coupon
              </Button>
            </Grid>
          </Grid>
        </Box>
      </LocalizationProvider>
    </div>
  );
};

export default AddNewCuponForm;
