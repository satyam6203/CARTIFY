import React from 'react'
import ProfileFieldCard from '../../../component/ProfileFieldCard'
import { Divider } from '@mui/material'

const UserDetails = () => {
  return (
    <div className='flex justify-center py-10'>
        <div className='w-full lg:w-[70%]'>
              <div className='flex items-center pb-3 justify-between'>
                  <h1 className='text-2xl font-bold text-gray-600'>Personal Details</h1>
              </div>
              <div className=''>
                  <ProfileFieldCard keys={'Name'} value={'Satyam Singh'}/>
                  <Divider/>
                  <ProfileFieldCard keys={'Mobile'} value={'6203909752'}/>
                  <Divider/>
                  <ProfileFieldCard keys={'Email'} value={'satyam@gmail.com'}/>
                  <Divider/>
                  <ProfileFieldCard keys={'city'} value={'Vadodara'}/>
              </div>
        </div>

    </div>
  )
}

export default UserDetails