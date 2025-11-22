import { TextField } from '@mui/material'
import React from 'react'

const BecomeSellerStep4 = ({formik}:any) => {
  return (
    <div className='space-y-5 mt-5'>
        <TextField
            fullWidth
            name='businessDetails.businessName'
            label='Business Name'
            value={formik.values.businessName}
            onChange={formik.handleChange}
            error={formik.touched.bankDetails?.businessName && Boolean(formik.errors.bankDetails?.businessName)}
            helperText={formik.touched.bankDetails?.businessName && formik.errors.bankDetails?.businessName}
        />
        <TextField
            fullWidth
            name='sellerName'
            label='Seller Name'
            value={formik.values.sellerName}
            onChange={formik.sellerName}
            error={formik.touched.bankDetails?.sellerName && Boolean(formik.errors.bankDetails?.sellerName)}
            helperText={formik.touched.bankDetails?.sellerName && formik.errors.bankDetails?.sellerName}
        />
        <TextField
            fullWidth
            name='email'
            label='Email'
            value={formik.values.email}
            onChange={formik.email}
            error={formik.touched.bankDetails?.email && Boolean(formik.errors.bankDetails?.email)}
            helperText={formik.touched.bankDetails?.email && formik.errors.bankDetails?.email}
        />
        <TextField
            fullWidth
            name='password'
            label='Password'
            value={formik.values.password}
            onChange={formik.password}
            error={formik.touched.bankDetails?.password && Boolean(formik.errors.bankDetails?.password)}
            helperText={formik.touched.bankDetails?.password && formik.errors.bankDetails?.password}
        />
    </div>
  )
}

export default BecomeSellerStep4