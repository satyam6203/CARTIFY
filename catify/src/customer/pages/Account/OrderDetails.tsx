import { Box, Button, Divider } from '@mui/material'
import React from 'react'
import { Navigate, useNavigate } from 'react-router-dom'
import OrderStepper from './OrderStepper';
import { Payments } from '@mui/icons-material';

const OrderDetails = () => {
    const navigate=useNavigate();
  return (
    <Box className="sapce-y-5">
        <section className='flex flex-col gap-5 justify-center items-center'>
            <img className='w-[100px]' src="https://res.cloudinary.com/dxoqwusir/image/upload/v1727452042/pro-ray-android-ios-cellecor-yes-original-imagydnsrany7qhy_1_m9n9t5.webp" alt="" />
            <div className='text-sm sapce-y-1 text-center'>
                <h1 className='font-bold'>{"Gada Electronics"}</h1>
                <p>Cellecor RAY 1.43" AMOLED Display | 700 NITS | ADD | BT-Calling | AI Voice | Split Screen Smartwatch (Black strap, Free Size)</p>
                <p><strong>Size : </strong>M</p>
            </div>
            <div>
                <Button onClick={()=>navigate(`/reviews/${5}/create`)}>
                    Write Review
                </Button>
            </div>
        </section>
        <section className='border p-5'>
            <OrderStepper orderStatus={"SHIPPED"}/>
        </section>

        <div className='border p-5'>
            <h1 className='font-bold pb-3'>Delivery Adderss</h1>
            <div className='text-sm space-y-2'>
                <div className='flex gap-5 font-medium'>
                    <p>{"Satyam Singh"}</p>
                    <p>{6203909752}</p>
                </div>

                <p>
                    parul university, Vadodara, Gujarat-790006
                </p>
            </div>
        </div>

        <div className='border space-y-4'>
            <div className='flex justify-between text-sm pt-5 px-5'>
                <div className='space-y-1'>
                    <p className='font-bold'>Total Item Price</p>
                    <p>You save <span className='text-green-500 font-medium text-xs'>{699}.00</span> on this item</p>
                </div>
                <p className='font-medium'>₹ {799}.00</p>
            </div>
            <div className='px-5'>
                <div className='bg-teal-50 px-5 py-2 text-xs font-medium flex items-center gap-3'>
                    <Payments/>
                    <p>Pay On Delivery</p>
                </div>
            </div>

            <Divider/>
            <div className='px-5 pb-5'>
                <p className='text-xs'><strong>Sold By : </strong>{"Gada Electronics"}</p>
            </div>
            <div className='p-10'>
                <Button disabled={false} color='error' sx={{py:"0.7rem"}} className='' variant='outlined' fullWidth>
                    {false?"order canceled":"Cancle Order"}
                </Button>
            </div>
        </div>
    </Box>
  )
}

export default OrderDetails