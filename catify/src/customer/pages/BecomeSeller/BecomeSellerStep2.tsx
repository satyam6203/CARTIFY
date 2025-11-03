import { Box, Button, Grid, TextField } from '@mui/material'
import { Formik, useFormik } from 'formik'
import React from 'react'
import * as Yup from 'yup'
const BecomeSellerStep2 = ({formik}:any) => {
  return (
    <Box sx={{max:"auto"}}>
        <p className='text-xl font-bold text-center pb-5 pt-5'>Pickup Address</p>
        <form onSubmit={formik.handleSubmit}>
          <Grid container spacing={3}>
              <Grid size={{xs:12}}>
                <TextField 
                  fullWidth
                  name='name'
                  label='Name'
                  value={formik.values.name}
                  onChange={formik.handleChange}
                  error={formik.touched.name && Boolean(formik.errors.name)}
                  helperText={formik.touched.name && formik.errors.name}
                />
              </Grid>
              <Grid size={{xs:6}}>
                <TextField 
                  fullWidth
                  name='mobile'
                  label='Mobile'
                  value={formik.values.mobile}
                  onChange={formik.handleChange}
                  error={formik.touched.name && Boolean(formik.errors.mobile)}
                  helperText={formik.touched.name && formik.errors.mobile}
                />
              </Grid>
              <Grid size={{xs:6}}>
                <TextField 
                  fullWidth
                  name='pinCode'
                  label='Pin Code'
                  value={formik.values.pinCode}
                  onChange={formik.handleChange}
                  error={formik.touched.pinCode && Boolean(formik.errors.pinCode)}
                  helperText={formik.touched.pinCode && formik.errors.pinCode}
                />
              </Grid>
              <Grid size={{xs:12}}>
                <TextField 
                  fullWidth
                  name='address'
                  label='address'
                  value={formik.values.address}
                  onChange={formik.handleChange}
                  error={formik.touched.address && Boolean(formik.errors.address)}
                  helperText={formik.touched.address && formik.errors.address}
                />
              </Grid>
              <Grid size={{xs:12}}>
                <TextField 
                  fullWidth
                  name='locality'
                  label='locality'
                  value={formik.values.locality}
                  onChange={formik.handleChange}
                  error={formik.touched.locality && Boolean(formik.errors.locality)}
                  helperText={formik.touched.name && formik.errors.locality}
                />
              </Grid>
              <Grid size={{xs:6}}>
                <TextField 
                  fullWidth
                  name='city'
                  label='city'
                  value={formik.values.city}
                  onChange={formik.handleChange}
                  error={formik.touched.city && Boolean(formik.errors.city)}
                  helperText={formik.touched.city && formik.errors.name}
                />
              </Grid>
              <Grid size={{xs:6}}>
                <TextField 
                  fullWidth
                  name='state'
                  label='state'
                  value={formik.values.state}
                  onChange={formik.handleChange}
                  error={formik.touched.state && Boolean(formik.errors.state)}
                  helperText={formik.touched.state && formik.errors.state}
                />
              </Grid>
              <Grid size={{xs:12}}>
                <Button type='submit' fullWidth variant='contained' sx={{py:'14px'}}>
                  Add Address
                </Button>
              </Grid>
          </Grid>
        </form>
    </Box>
  )
}

export default BecomeSellerStep2