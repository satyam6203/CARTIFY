import React, { useState } from "react";
import CartItem from "./CartItem";
import { Close, LocalOffer } from "@mui/icons-material";
import { teal } from "@mui/material/colors";
import { Button, IconButton, TextField } from "@mui/material";
import PricingCart from "./PricingCart";
import { useNavigate } from "react-router-dom";

const Cart = () => {
  const [couponCode, setCouponCode] = useState("");
  const handelChange = (e: any) => {
    setCouponCode(e.target.value);
  };
  const navigate=useNavigate();
  return (
    <div className="pt-10 px-5 sm:px-10 md:px-60 min-h-screen">
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-5">
        <div className="cartItemSection lg:col-span-2 space-y-3">
          {[1, 1, 1, 1, 1, 1].map((item) => (
            <CartItem />
          ))}
        </div>
        <div className="col-span-1 text-sm space-y-4">
          <div className="border rounded-md px-5 py-3 space-y-5">
            <div className="flex gap-3 text-sm items-center">
              <div className="flex gap-3 text-sm items-center">
                <LocalOffer sx={{ color: teal[500], fontSize: "17px" }} />
              </div>
              <span>Applay Coupons</span>
            </div>
            {true ?<div className="flex justify-between items-center">
              <TextField
                onChange={handelChange}
                id="outlined-basic"
                size="small"
                placeholder="coupon code"
                variant="outlined"
              />
              <Button size="small">Apply</Button>
            </div>
            :<div className="flex">
              <div className="p-1 pl-5 pr-3 border rounded-md flex gap-2 items-center"> 
                  <span className="">Applied</span>
                  <IconButton size="small">
                    <Close className="text-red-600"/>
                  </IconButton>
              </div>
            </div>}
          </div>
          <div className="border rounded-md">
            <PricingCart/>
            <div className="p-5">
              <Button onClick={()=>navigate("/pay")} fullWidth variant="contained" sx={{py:'11px'}}>
                Buy Now
              </Button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Cart;
