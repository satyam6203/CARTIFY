import React from 'react'
import StarIcon from '@mui/icons-material/Star';
import { teal } from '@mui/material/colors';
import { Button, Divider } from '@mui/material';
import { Add, AddShoppingCart, FavoriteBorder, LocalShipping, Remove, Shield, Wallet, WorkspacePremium } from '@mui/icons-material';
import SimilarProduct from './SimilarProduct';
import Review from '../Review/Review';
import ReviewCard from '../Review/ReviewCard';
const ProductDetails = () => {
  const [quantity,setQuantity]=React.useState(1);
  return (
    <div className='px-5 lg:px-20 pt-10'>
      <div className='grid grid-cols-1 lg:grid-cols-2 gap-10'>
        <section className='flex flex-col lg:flex-row gap-5'>
          <div className='w-full lg:w-[15%] flex flex-wrap lg:flex-col gap-3'>
            {[1,1,1,1].map((item)=><img  className='lg:w-full w-[50px] cursor-pointer rounded-md'
            src='http://res.cloudinary.com/dxoqwusir/image/upload/v1727451187/SoftSilkZariBanarasiSaree_1_fwms3W.jpg'alt=''/>)}
          </div>
          <div className='w-full lg:w-[85%]'>
            <img className='w-full rounded-md' src="https://res.cloudinary.com/dxoqwusir/image/upload/v1727451205/SoftSilkZariBanarasiSaree_4_fyohzg.jpg" alt="" />
          </div>
        </section>

        <section >
              <h1 className='font-bold text-lg text-primary-color'>Raam Clothing</h1>
              <p className='text-gray-500 font-semibold'>Women Banarasi Saree</p>
              <div className='flex justify-between items-center py-2 border w-[180px] px-3 mt-5'>
                  <div className='flex gap-1 items-center'>
                      <span className='text-gray-500'>4</span>
                      <StarIcon sx={{color:teal[500],fontSize:'17px'}}/>
                  </div>
                  <Divider orientation='vertical' flexItem/>
                  <span className='text-gray-500'>234 Ratings</span>
              </div>
              <div>
                  <div className="price flex items-center gap-3 mt-5 text-2xl">
                    <span className="font-sans text-gray-800">
                      ₹ 1149
                    </span>
                    <span className="line-through text-gray-400">
                      ₹ 1999
                    </span>
                    <span className="text-primary-color font-semibold">
                      39% off
                    </span>
                  </div>
                  <p className='text-sm font-semibold'>
                    Inclusive of all texes. Free Shipping above ₹1500.
                  </p>
              </div>
              <div className='mt-7 space-y-3'>
                  <div className='flex items-center gap-4'>
                    <Shield sx={{color:teal[500]}}/>
                    <p>Auhtentic & Quality Assured</p>
                  </div>
                  <div className='flex items-center gap-4'>
                    <WorkspacePremium sx={{color:teal[500]}}/>
                    <p>100% money back gaurantee</p>
                  </div>
                  <div className='flex items-center gap-4'>
                    <LocalShipping sx={{color:teal[500]}}/>
                    <p>Free Shipping & Returns</p>
                  </div>
                  <div className='flex items-center gap-4'>
                    <Wallet sx={{color:teal[500]}}/>
                    <p>Pay on delivery might be available</p>
                  </div>
              </div>
              <div className='mt-7 sapce-y-2'>
                  <h1 className='text-gray-500 font-semibold'>
                    QUANTITY
                  </h1>
                  <div className='flex items-center gap-2 w-[140px] justify-between'>
                    <Button variant='outlined'
                      disabled={quantity ==1} onClick={()=>setQuantity(quantity-1)}>
                      <Remove/>
                    </Button>
                    <span className='text-gray-500 font-semibold'>
                      {quantity}
                    </span>
                    <Button variant='outlined' onClick={()=>setQuantity(quantity+1)}>
                      <Add/>
                    </Button>
                  </div>
              </div>

              <div className='mt-10 flex items-center gap-5'>
                  <Button 
                  fullWidth
                  variant='contained'
                  startIcon={<AddShoppingCart/>}
                  sx={{py:'1rem'}}>
                    Add To Beg
                  </Button>

                  <Button 
                  fullWidth
                  variant='outlined'
                  startIcon={<FavoriteBorder/>}
                  sx={{py:'1rem'}}>
                    Wishlist 
                  </Button>
              </div>
              <Divider className='pt-5'/>
              <div className='mt-5'>
                <p>This Banarashi saree is a perfect blend of tradition and elegance. Its intricate designs and rich fabric make it stand out in any celebration. Ideal for weddings, festivals, or special occasions.</p>
              </div>
              <div className='mt-12  space-y-12'>
                <ReviewCard/>

              </div>
        </section>
      </div>
      <div className='mt-20'>
        <h1 className='text-lg font-bold'>Similar Product</h1>
        <div className='pt-5'>
          <SimilarProduct/>
        </div>
      </div>
    </div>
  )
}

export default ProductDetails