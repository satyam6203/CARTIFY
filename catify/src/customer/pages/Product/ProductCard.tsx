import React, { useEffect, useState } from "react";
import "./ProductCard.css";
import { Button } from "@mui/material";
import { Favorite, ModeComment } from "@mui/icons-material";
import { teal } from "@mui/material/colors";

const images = [
  "https://res.cloudinary.com/dxoqwusir/image/upload/v1727460133/4QdHw1UN_f8db19fa1b1947689b2cc1f461b25b14_fc2y1j.jpg",
  "https://res.cloudinary.com/dxoqwusir/image/upload/v1727460139/6ip1jSbB_f6fe477ff55a4f9fa3f929f3c2d28ad9_ikswnv.jpg",
];
const ProductCard = () => {
  const [currentImage, setCurrentImage] = useState(0);
  const [isHovered, setIsHovered] = useState(false);

  useEffect(() => {
    let interval: any;

    if (isHovered) {
      interval = setInterval(() => {
        setCurrentImage((prevImage) => (prevImage + 1) % images.length);
      }, 1000);
    } else if (interval) {
      clearInterval(interval);
      interval = null;
    }
    return () => clearInterval(interval);
  }, [isHovered]);

  return (
    <div className="group px-4 relative">
      <div
        className="card"
        onMouseEnter={() => setIsHovered(true)}
        onMouseLeave={() => setIsHovered(false)}
      >
        {images.map((item, index) => (
          <img
            className="card-media object-top"
            src={item}
            alt=""
            style={{
              transform: `translateX(${(index - currentImage) * 100}%)`,
            }}
          ></img>
        ))}
        {isHovered &&
          <div className="indicator flex flex-col items-center space-y-2">
            <div className="flex gap-3">
              <Button variant="contained" color="secondary">
                <Favorite sx={{ color: teal[500] }} />
              </Button>
              <Button variant="contained" color="secondary">
                <ModeComment sx={{ color: teal[500] }} />
              </Button>
            </div>
          </div>
        }
      </div>
      <div className="details pt-3 space-y-1 group-hover-effect rounded-md">
          <div className="name">
            <h1>Niky</h1>
            <p>Blue Shirt</p>
          </div>
          <div className="price flex items-center gap-3">
            <span className="font-sans text-gray-800">
              ₹ 400
            </span>
            <span className="thin-line-through text-gray-400">
              ₹ 999
            </span>
            <span className="text-primary-color font-semibold">
              60%
            </span>
          </div>
      </div>
    </div>
  );
};

export default ProductCard;
