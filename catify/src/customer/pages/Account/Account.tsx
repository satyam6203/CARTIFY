import { Divider } from '@mui/material'
import path from 'path'
import React from 'react'
import { Route, Routes, useLocation, useNavigate } from 'react-router-dom'
import Orders from './Orders'
import OrderDetails from './OrderDetails'
import OrderStepper from './OrderStepper'
import UserDetails from './UserDetails'
import Address from './Address'

const menu=[
  {name:"orders" ,path:"/account/orders"},
  {name:"profile",path:"/account"},
  {name:"Save Cards",path:"/account/saved-card"},
  {name:"Address",path:"/account/address"},
  {name:"Logout",path:'/'}
]
const Account = () => {
  const navigate=useNavigate();
  const handleClick=(item:any)=>navigate(item.path);
  const location=useLocation();
  return (
    <div className='px-5 lg:px-52 min-h-screen mt-10'>
      <div>
        <h1 className='text-xl font-bold pb-5'>Cartify</h1>
      </div>
      <Divider/>
      <div className='grid grid-col-1 lg:grid-cols-3 lg:min-h-[78vh]'>
        <section className='col-span-1 lg:pr-5 lg:border-r py-5 h-full'>
          {
            menu.map((items)=>(
              <div onClick={()=>handleClick(items)} key={items.name}
                className={`${items.path===location.pathname?"bg-primary-color text-white":""}
                py-3 cursor-pointer hover:text-white hover:bg-primary-color px-5 rounded-md border-b`}>
                <p>{items.name}</p>
              </div>
            ))
          }
        </section>
        <section className='right lg:col-span-2 lg:pl-5 py-5'>
          <Routes>
            <Route path='/' element={<UserDetails/>}/>
            <Route path='/orders' element={<Orders/>}/>
            <Route path='/orders/:orderId/:orderItemId' element={<OrderDetails/>}/>
            <Route path='/address' element={<Address/>}/>
            <Route path='/order-details' element={<OrderDetails/>}/>
          </Routes>
        </section>
      </div>
    </div>
  )
}

export default Account