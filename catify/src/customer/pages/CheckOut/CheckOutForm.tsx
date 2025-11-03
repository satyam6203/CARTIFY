import { Box, Button, FormControlLabel, Modal, Radio, RadioGroup, Typography } from "@mui/material";
import React from "react";
import AddressCArd from "./AddressCard";
import { Add, AddShoppingCart } from "@mui/icons-material";
import AddressForm from "./AddressForm";
import PricingCart from "../Cart/PricingCart";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 500,
  bgcolor: "background.paper",
  boxShadow: 24,
  p: 4,
};
const paymentGetWayList=[
    {
        value:"RAZORPAY",
        image:"https://razorpay.com/newsroom-content/uploads/2020/12/output-onlinepngtools-1-1.png",
        leble:""
    },
    {
        value:"STRIPE",
        image:"https://cdn.dribbble.com/userupload/14307859/file/original-41e703988acbbb7f037f7065ba10603a.jpg",
        leble:""
    }
]
const CheckOutForm = () => {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [paymentGetWay,setPaymentGetway]=React.useState("RAZORPAY")
  const handlePaymentChnage=(event:any)=>{
    setPaymentGetway(event.target.value);
  };
  return (
    <>
      <div className="pt-10 px-5 sm:px-10 md:px-44 lg:px-60 min-h-screen">
        <div className="space-y-5 lg:space-y-0 lg:grid grid-cols-3 lg:gap-9">
          <div className="col-span-2 span-y-5">
            <div className="flex justify-between items-center">
              <h1>Select Address</h1>
              <Button onClick={handleOpen}>
                Add new Address
                <Add />
              </Button>
            </div>
            <div className="text-xs font-medium space-y-5">
              <p>Saved Addresses</p>
              <div className="space-y-3">
                {[1, 1, 1].map((itmes) => (
                  <AddressCArd />
                ))}
              </div>
            </div>
            <div className="py-4 px-5 rounded-md border mt-5">
              <Button onClick={handleOpen}>
                Add new Address
                <Add />
              </Button>
            </div>
          </div>
          <div>
            <div>
                <div className="space-y-3 border p-5 rounded-md">
                    <h1 className="text-primary-color font-medium pb-2">Chose Payment Method</h1>
                    <RadioGroup
                    row
                    aria-labelledby="demo-row-radio-buttons-group-label"
                    name="row-radio-buttons-group"
                    className="flex justify-between pr-0"
                    onChange={handlePaymentChnage}
                    value={paymentGetWay}
                    >
                    {paymentGetWayList.map((items)=>
                    <FormControlLabel
                    className="border w-[45%]"
                        value={items.value}
                        control={<Radio />}
                        label={
                            <img className={`${items.value=="stripe"?"w-14":""}`}
                            src={items.image} alt="" />
                        }
                    />)}
                    
                    </RadioGroup>
                </div>
            </div>
            <div className="border rounded-md">
              <PricingCart />
              <div className="p-5">
                <Button fullWidth variant="contained" sx={{ py: "11px" }}>
                  Pay Now
                </Button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <AddressForm />
        </Box>
      </Modal>
    </>
  );
};

export default CheckOutForm;
