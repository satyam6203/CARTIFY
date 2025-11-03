import React, { useState } from 'react'
import SellerAccoutnForm from './SellerAccoutnForm';
import SellerLoginForm from './SellerLoginForm';
import { Button } from '@mui/material';
import sellerBanner from '../../components/images/Seller-banner.jpg';
const BecomeSeller = () => {
    const[isLogin,setLogin]=useState(false);
    const handelShowPage=()=>{
        setLogin(!isLogin);
    }
  return (
    <div className='grid md:gap-10 grid-cols-3 min-h-screen'>
        <section className='lg:col-span-1 md:col-span-2 col-span-3 p-10 shadow-lg rounded-b-md'>
            {!isLogin?<SellerAccoutnForm/>:<SellerLoginForm/>}
            <div className='mt-10 space-y-2'>
                 <h1 className='text-center text-sm font-medium'>Have Account</h1>
                 <Button onClick={handelShowPage} fullWidth sx={{py:"11px"}} variant='outlined'>
                    {isLogin?"Register":"Login"}
                 </Button>
            </div>
        </section>
        <section className='hidden md:col-span-1 lg:col-span-2 md:flex justify-center items-center'>
            <div className='lg:w-[70%] px-5 space-y-8'>
                <div className='space-y-2 font-bold text-center'>
                    <p className='text-2xl'>Join the MasterPice Revolution</p>
                    <p className='text-lg text-primary-color'>Boost Your Sales today</p>
                </div>
                <img src={sellerBanner} alt="" />
            </div>
        </section>
    </div>
  )
}

export default BecomeSeller