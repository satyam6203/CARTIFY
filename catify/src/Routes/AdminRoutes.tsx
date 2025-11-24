import React from 'react'
import SellerTable from '../admin/Pages/Seller/SellerTable'
import { Route, Routes } from 'react-router-dom'
import Coupon from '../admin/Pages/coupon/coupon'
import AddNewCuponForm from '../admin/Pages/coupon/AddNewCuponForm'
import GridTable from '../admin/Pages/HomePage/GridTable'
import ElectronicTable from '../admin/Pages/HomePage/ElectronicTable'
import ShopByCategory from '../admin/Pages/HomePage/ShopByCategory'
import Deal from '../admin/Pages/HomePage/Deal'

const AdminRoutes = () => {
  return (
    <Routes>
        <Route path='/' element={<SellerTable/>}/>
        <Route path='/coupon' element={<Coupon/>}/>
        <Route path='/add-coupon' element={<AddNewCuponForm/>}/>
        <Route path='/home-grid' element={<GridTable/>}/>
        <Route path='/electronics-category' element={<ElectronicTable/>}/>
        <Route path='/shop-by-category' element={<ShopByCategory/>}/>
        <Route path='/deals' element={<Deal/>}/>
    </Routes>
  )
}

export default AdminRoutes