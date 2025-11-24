import React from 'react'
import { Route, Routes } from 'react-router-dom'
import SellerDashboard from '../seller/pages/SellerDashBoard/SellerDashboard'
import DashBoard from '../seller/pages/SellerDashBoard/DashBoard'
import Orders from '../seller/pages/Orders/Orders'
import Profile from '../seller/pages/Account/Profile'
import Payment from '../seller/pages/Payment/Payment'
import Transaction from '../seller/pages/Payment/Transaction'
import Product from '../seller/pages/Products/Product'
import AddProduct from '../seller/pages/Products/AddProduct'

const SellerRoutes = () => {
  return (
    <div>
        <Routes>
            <Route path="/*" element={<DashBoard/>}/>
            <Route path="/products" element={<Product/>}/>
            <Route path="/orders" element={<Orders/>}/>
            <Route path="/account" element={<Profile/>}/>
            <Route path="/payment" element={<Payment/>}/>
            <Route path="/transaction" element={<Transaction/>}/>
            <Route path="/add-product" element={<AddProduct/>}/>
        </Routes>
    </div>
  )
}

export default SellerRoutes