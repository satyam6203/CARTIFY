import React, { useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { useAppDispatch } from '../../Redux Toolkit/Store';
import { verifySellerEmail } from '../../Redux Toolkit/Seller/sellerSlice';

const SellerAccountVerification = () => {
  const { otp } = useParams();
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  useEffect(() => {
    if (otp) {
      dispatch(verifySellerEmail({ otp: Number(otp), navigate }));
    }
  }, [otp, dispatch, navigate]);

  return (
    <div className='text-center mt-20 text-xl'>
      Verifying your seller account, please wait...
    </div>
  )
}

export default SellerAccountVerification;
