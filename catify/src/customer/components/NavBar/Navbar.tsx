import { Avatar, Box, Button, IconButton, useMediaQuery, useTheme } from '@mui/material'
import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import React, { useState } from 'react'
import { AddShoppingCart, FavoriteBorder, Storefront } from '@mui/icons-material';
import CategorySheet from './CategorySheet';
import { mainCategory } from '../../../data/category/mainCategory';

const Navbar = () => {
    const theme=useTheme();
    const isLarge=useMediaQuery(theme.breakpoints.up("lg"));
    const [selectedCategory,setSelectedCategory]=useState("men");
    const [showCategorySheet,setShowCategorySheet]=useState(false);

  return (
    <div>
        <Box className="sticky top-0 right-0 left-0 bg-white" sx={{zIndex:2}}>
            <div className='flex items-center justify-between px-5 lg:px-20 h-[70px] border-b'>
                <div className='flex items-center gap-9' >
                    <div className='flex items-center gap-2'>
                       {!isLarge && <IconButton>
                            <MenuIcon/>
                        </IconButton>}
                        <h1 className='logo cursor-pointer text-lg md:text-2xl text-primary-color'>
                            CartiFy
                        </h1>
                    </div>
                    <ul className='flex items-center  font-medium text-gray-800'>
                            {
                                mainCategory.map((item)=>
                                    <li 
                                onMouseLeave={()=>{
                                    setShowCategorySheet(false);
                                }}
                                onMouseEnter={()=>{
                                    setShowCategorySheet(true);
                                    setSelectedCategory(item.categoryId);
                                }}
                                className='mainCategory hover:text-primary-color
                                    hover:border-b-2 h-[70px] px-4 border-primary-color  flex items-center'>
                                        {item.name}
                                    </li>
                                )
                            }
                    </ul>
                </div>
                <div className='flex gap-1 lg:gap-6 items-center'>
                    <IconButton>
                        <SearchIcon/>
                    </IconButton>
                    {
                        false?<Button className='flex items-center gap-2'>
                            <Avatar
                                sx={{width:29,height:29}}
                                src='https://cdn.pixabay.com/photo/2015/04/15/09/28/head-723540_640.jpg' 
                            />
                            <h1 className='font-semibold hidden lg:block'>
                                Satyam
                            </h1>
                        </Button>:<Button variant='contained'>Login</Button>
                    }
                    <IconButton>
                        <FavoriteBorder sx={{fontSize:29}}/>
                    </IconButton>
                    <IconButton>
                        <AddShoppingCart className='text-gray-700' sx={{fontSize:29}}/>
                    </IconButton>
                   {isLarge && <Button startIcon={<Storefront/>} variant='outlined'>
                        Become Seller
                    </Button>}
                </div>
            </div>
            {showCategorySheet && <div 
            onMouseLeave={()=>setShowCategorySheet(false)}
            onMouseEnter={()=>setShowCategorySheet(true)}
            className='categorySheet absolute top-[4.41rem] left-20 right-20'>
                <CategorySheet selectedCategory={selectedCategory}/>
            </div>
            }
        </Box>
    </div>
  )
}

export default Navbar