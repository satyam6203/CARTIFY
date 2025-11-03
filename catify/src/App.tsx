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
import Review from './customer/pages/Review/Review';
import Cart from './customer/pages/Cart/Cart';
import CheckOutForm from './customer/pages/CheckOut/CheckOutForm';
import Account from './customer/pages/Account/Account';
import { Route, Routes } from 'react-router-dom';
import BecomeSeller from './customer/pages/BecomeSeller/BecomeSeller';
import OrderDetails from './customer/pages/Account/OrderDetails';




function App() {
  return (
    <ThemeProvider theme={coustomeTheme}>
      <div>
        {/* <Home/> */}
        {/* <Product/> */}
        {/* <ProductDetails/> */}
        {/* <Review/> */}
        {/* <Cart/> */}
        {/* <CheckOutForm/> */}
        {/* <Account/> */}
        <Navbar/>
        <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/products/:category" element={<Product/>}/>
          <Route path="/reviews/:productId" element={<Review/>}/>
          <Route path="/product-details/:categoryId/:name/:productId" element={<ProductDetails/>}/>
          <Route path="/Cart" element={<Cart/>}/>
          <Route path="/Pay" element={<CheckOutForm/>}/>
          <Route path="/Become-Seller" element={<BecomeSeller/>}/>
          <Route path="/account/*" element={<Account/>}/>
        </Routes>
      </div>
    </ThemeProvider>
  );
}

export default App;
