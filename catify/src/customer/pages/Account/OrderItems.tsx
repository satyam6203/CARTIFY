import { ElectricBolt } from '@mui/icons-material'
import { Avatar } from '@mui/material'
import { teal } from '@mui/material/colors'
import React from 'react'
import { useNavigate } from 'react-router-dom'

const OrderItems = () => {
  const navigate=useNavigate();
  return (
    <div onClick={()=>navigate("/order-details")} className='text-sm bg-white p-5 space--y-5 border rounded-md cursor-pointer'>
        <div className='flex items-center gap-5'>
            <div>
              <Avatar sizes='small' sx={{bgcolor:teal[500]}}>
                <ElectricBolt/>
              </Avatar>
            </div>
            <div>
              <h1 className='font-bold text-primary-color'>PENDING</h1>
              <p>Arriving By Mon, 15 Nov</p>
            </div>
        </div>
        <div className='p-5 bg-teal-50 flex gap-3'>
            <div>
              <img className='w-[70px]' src="https://res.cloudinary.com/dxoqwusir/image/upload/v1727452042/pro-ray-android-ios-cellecor-yes-original-imagydnsrany7qhy_1_m9n9t5.webp" alt="" />
            </div>
            <div className='w-full sapce-y-2'>
              <h1 className='font-bold'>Gada Electronics</h1>
              <p>Cellecor RAY 1.43" AMOLED Display | 700 NITS | ADD | BT-Calling | AI Voice |Split Screen Smartwatch (Black strap, Free Size)</p>
              <p>
                <strong>
                  size: 
                </strong>
                FREE
              </p>
            </div>
        </div>
    </div>
  )
}

export default OrderItems