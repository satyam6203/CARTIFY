
import { Delete } from '@mui/icons-material'
import { Avatar, Box, Grid, IconButton, Rating } from '@mui/material'
import { red } from '@mui/material/colors'
import React from 'react'

const ReviewCard = () => {
  return (
    <div className='flex justify-between'>
        <Grid container spacing={9}>
            <Grid size={{xs:1}}>
                <Box>
                    <Avatar className='text-white' sx={{width:56,height:56,bgcolor:"#9155FD"}}>
                        KM
                    </Avatar>
                </Box>
            </Grid>
            <Grid size={{xs:9}}>
            <div className='space-y-2'>
                <div>
                    <p className='font-semibold text-lg'>
                        K Mishra
                    </p>
                    <p className='opacity-70'>2024-09-27T23:16:07.478333</p>
                </div>
            </div>
            <Rating readOnly value={4.5} precision={1}/>
            <p>value for money product, great product</p>
            <div>
                <img className='w-24 h-24 object-cover rounded-md' src="http://res.cloudinary.com/dxoqwusir/image/upload/v1727459152/purchased_product_b19fgy.jpg" alt="" />
            </div>
        </Grid>
        </Grid>
        <div>
            <IconButton>
                <Delete sx={{color:red[700]}}/>
            </IconButton>
        </div>
    </div>
  )
}

export default ReviewCard