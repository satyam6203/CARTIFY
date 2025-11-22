import { TextField } from '@mui/material'
import React from 'react'

const BecomeSellerStep3 = ({formik}:any) => {
  return (
    <div className='space-y-5 mt-5'>
        <TextField
            fullWidth
            name='bankDetails.accountNumber'
            label='Account Number'
            value={formik.values.accountNumber}
            onChange={formik.handleChange}
            error={formik.touched.bankDetails?.accountNumber && Boolean(formik.errors.bankDetails?.accountNumber)}
            helperText={formik.touched.bankDetails?.accountNumber && formik.errors.bankDetails?.accountNumber}
        />
        <TextField
            fullWidth
            name='bankDetails.ifcsCode'
            label='IFSC Code'
            value={formik.values.ifcsCode}
            onChange={formik.handleChange}
            error={formik.touched.bankDetails?.ifcsCode && Boolean(formik.errors.bankDetails?.ifcsCode)}
            helperText={formik.touched.bankDetails?.ifcsCode && formik.errors.bankDetails?.ifcsCode}
        />
        <TextField
            fullWidth
            name='bankDetails.accountHolderName'
            label='Account Holder Name'
            value={formik.values.accountHolderName}
            onChange={formik.handleChange}
            error={formik.touched.bankDetails?.accountHolderName && Boolean(formik.errors.bankDetails?.accountHolderName)}
            helperText={formik.touched.bankDetails?.accountHolderName && formik.errors.bankDetails?.accountHolderName}
        />
    </div>
  )
}

export default BecomeSellerStep3