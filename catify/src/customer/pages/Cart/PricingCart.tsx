import { Divider } from '@mui/material'
import React from 'react'

const PricingCart = () => {
  return (
    <div>
      <div className='space-y-3 p-5'>
          <div className='flex justify-between items-center'>
            <span className='text-gray-500'>Subtotal</span>
            <span className='text-gray-700'>899</span>
          </div>
          <div className='flex justify-between items-center'>
            <span className='text-gray-500'>Discount</span>
            <span className='text-gray-700'>699</span>
          </div>
          <div className='flex justify-between items-center'>
            <span className='text-gray-500'>Shipping</span>
            <span className='text-gray-700'>69</span>
          </div>
          <div className='flex justify-between items-center'>
            <span className='text-gray-500'>Plateform Fee</span>
            <span className='text-primary-color'>Free</span>
          </div>
      </div>
      <Divider/>
          <div className='flex justify-between items-center p-5'>
            <span className='text-primary-color'>Total</span>
            <span className='text-primary-color'>1000</span>
          </div>
    </div>
  )
}

export default PricingCart