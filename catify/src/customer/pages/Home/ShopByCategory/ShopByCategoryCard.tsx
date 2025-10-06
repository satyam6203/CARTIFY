import React from 'react'
import './ShopByCategory.css'

const ShopByCategoryCard = () => {
  return (
    <div className='flex gap-3 flex-col justify-center items-center group cursor-pointer'>
        <div className='custome-border w-[150px] h-[150px] lg:w-[249px] lg:h-[249px] rounded-full bg-primary-color'>
            <img  className='rounded-full group-hover:scale-95 transition-transform transfrom-duration-700 object-cover object-top h-full w-full'
            src='https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/19873492/2022/9/10/fd72939c-c379-4bab-9fd6-918c89172d161662797928387AquarelleBlue100CottonPrintedTableRunner1.jpg' alt=""/>
        </div>
        <h1>Kitchen & Table</h1>
    </div>
  )
}

export default ShopByCategoryCard