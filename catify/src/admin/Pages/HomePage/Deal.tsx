import React, { useState } from 'react'
import HomeCategoryTable from './HomeCategoryTable'
import { Button } from '@mui/material'
import DealTable from './DealTable'
import DealCategory from './DealCategory'
import CreateDealForm from './CreateDealForm'
const tabs=[
  "Deals",
  "Category",
  "Create Deal"
]
const Deal = () => {
  const [acviveTab,setActivTab]=useState("Deals");
  return (
    <div className='mt-5'>
      <div className='flex gap-4'>
          {tabs.map((item)=><Button onClick={()=>setActivTab(item)} variant={acviveTab==item?"contained":"outlined"}>{item}</Button>)}
      </div>
      <div className='pt-5'>
          {acviveTab=="Deals"?<DealTable/>:acviveTab=="Category"?<DealCategory/>:
          <div className='mt-5 flex flex-col justify-center items-center h-[70vh]'>
            <CreateDealForm/>
          </div>}
      </div>
    </div>
  )
}

export default Deal