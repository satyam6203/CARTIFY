import { Label } from "@mui/icons-material";
import { Button, Step, StepLabel, Stepper } from "@mui/material";
import { useFormik } from "formik";
import React, { useState } from "react";
import BecomeSellerStep1 from "./BecomeSellerStep1";
import BecomeSellerStep2 from "./BecomeSellerStep2";
import BecomeSellerStep3 from "./BecomeSellerStep3";
import BecomeSellerStep4 from "./BecomeSellerStep4";

const steps = [
  "Tax Details & Mobile",
  "Pickup Address",
  "Bank Details",
  "Supplier Details",
];
const SellerAccoutnForm = () => {
  const [activeStep, setActiveStep] = useState(0);
  const handleStep = (value: number) => () => {
    (activeStep < steps.length - 1 || (activeStep > 0 && value == -1)) &&
      setActiveStep(activeStep + value);
    activeStep == steps.length - 1 && handleCreateAccount();
  };
  const handleCreateAccount = () => {
    console.log("account is Created");
  };
  const formik = useFormik({
    initialValues: {
      mobile: "",
      phone: "",
      gstin: "",
      pickupAddress: {
        name: "",
        mobile: "",
        pincode: "",
        address: "",
        locality: "",
        city: "",
        state: "",
      },
      bankDetails: {
        accountNumber: "",
        ifscCode: "",
        accoutnHolderName: "",
      },
      sellerName: "",
      email: "",
      businessDetails: {
        businessName: "",
        businessEmail: "",
        businessMobile: "",
        logo: "",
        banner: "",
        businessAddress: "",
      },
      password: "",
    },
    onSubmit: (values) => {
      console.log(values, "formik submitted");
    },
  });
  return (
    <div>
      <Stepper activeStep={activeStep} alternativeLabel>
        {steps.map((label, index) => (
          <Step key={label}>
            <StepLabel>{label}</StepLabel>
          </Step>
        ))}
      </Stepper>
      <section className="mt-15 space-y-10">
        <div>
          {activeStep == 0 ? <BecomeSellerStep1 formik={formik} /> :
          activeStep==1?<BecomeSellerStep2 formik={formik}/>:
          activeStep==2?<BecomeSellerStep3 formik={formik}/> :
          <BecomeSellerStep4 formik={formik}/>

          }
        </div>
        <div className="flex items-center justify-between">
          <Button
            onClick={handleStep(-1)}
            variant="contained"
            disabled={activeStep == 0}
          >
            Back
          </Button>
          <Button onClick={handleStep(1)} variant="contained">
            {activeStep == steps.length - 1 ? "Create Account" : "continue"}
          </Button>
        </div>
      </section>
    </div>
  );
};

export default SellerAccoutnForm;
