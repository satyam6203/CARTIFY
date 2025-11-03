import { Radio } from '@mui/material'
import React from 'react'

const AddressCArd = () => {
  const handleChange=(event:any)=>{
    console.log(event.target.checked);
  }
  return (
    <div className='p-5 border rounded-md flex'>
      <div>
        <Radio
        checked={true}
        onChange={handleChange}
        value=""
        name='radio-button'
        />
      </div>

      <div className='space-y-3 pt-3'>
        <h1 className='text-lg'>Satyam</h1>
        <p>Parul University Vadodara,Gujarat</p>
        <p><strong className='text-gray-700'>Mobile: </strong>6203909752</p>
      </div>
    </div>
  )
}

export default AddressCArd