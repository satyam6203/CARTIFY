import React from "react";

import SellerRoutes from "../../../routes/SellerRoutes";
import Navbar from "../../../admin seller/components/navbar/Navbar";
import SellerDrawerList from "../../components/SideBar/DrawerList";

const SellerDashboard = () => {
  return (
    <div className="h-screen flex flex-col">
      <Navbar DrawerList={SellerDrawerList}/>
      <section className="flex flex-1 overflow-hidden">
        <div className="hidden lg:block h-full">
        <SellerDrawerList/>
        </div>
        <div className="p-10 w-full lg:w-[80%] overflow-y-auto">
          <SellerRoutes />
        </div>
      </section>
    </div>
  );
};

export default SellerDashboard;
