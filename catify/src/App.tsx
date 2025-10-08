import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Button, ThemeProvider } from '@mui/material';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import Navbar from './customer/components/NavBar/Navbar';
import coustomeTheme from './theme/CustomTheme';
import Home from './customer/pages/Home/Home'
import Product from './customer/pages/Product/Product';
import ProductDetails from './customer/pages/Page Details/ProductDetails';


function App() {
  return (
    <ThemeProvider theme={coustomeTheme}>
      <div>
        <Navbar/>
        {/* <Home/> */}
        {/* <Product/> */}
        <ProductDetails/>
      </div>
    </ThemeProvider>
  );
}

export default App;
