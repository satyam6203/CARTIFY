import { styled } from "@mui/material/styles";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import React, { useState } from "react";
import { Button, FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import { Delete } from "@mui/icons-material";
const couponStatuses = [
  {
    status: "ACTIVE",
    title: "Active",
    description: "Coupon is currently active and usable by customers.",
  },
  {
    status: "EXPIRED",
    title: "Expired",
    description: "Coupon has expired and is no longer valid.",
  },
  {
    status: "UPCOMING",
    title: "Upcoming",
    description: "Coupon is scheduled for future availability but not active yet.",
  },
  {
    status: "DISCONTINUED",
    title: "Discontinued",
    description: "Coupon was manually disabled and will not be used anymore.",
  },
  {
    status: "LIMIT_REACHED",
    title: "Limit Reached",
    description:
      "Coupon usage limit has been reached and cannot be redeemed further.",
  },
  {
    status: "INVALID",
    title: "Invalid",
    description: "Coupon is invalid due to incorrect configuration or code.",
  },
];

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  "&:nth-of-type(odd)": {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

function createData(
  name: string,
  calories: number,
  fat: number,
  carbs: number,
  protein: number
) {
  return { name, calories, fat, carbs, protein };
}

const rows = [
  createData("Frozen yoghurt", 159, 6.0, 24, 4.0),
  createData("Ice cream sandwich", 237, 9.0, 37, 4.3),
  createData("Eclair", 262, 16.0, 24, 6.0),
  createData("Cupcake", 305, 3.7, 67, 4.3),
  createData("Gingerbread", 356, 16.0, 49, 3.9),
];
const coupon = () => {
  // const [accountStatus, setAccountStatus] = useState("ACTIVE");
  // const handleChange = (event: any) => {
  //   setAccountStatus(event.target.value);
  // };
  return (
    <>
      <div className="w-60">
      </div>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCell>Coupon Code</StyledTableCell>
              <StyledTableCell>Start Date</StyledTableCell>
              <StyledTableCell>End Date</StyledTableCell>
              <StyledTableCell align="right">Minimum Order Value</StyledTableCell>
              <StyledTableCell align="right">Discount</StyledTableCell>
              <StyledTableCell align="right">Delete</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <StyledTableRow key={row.name}>
                <StyledTableCell component="th" scope="row">
                  {row.name}
                </StyledTableCell>
                <StyledTableCell>{row.calories}</StyledTableCell>
                <StyledTableCell align="right">{row.fat}</StyledTableCell>
                <StyledTableCell align="right">{row.carbs}</StyledTableCell>
                <StyledTableCell align="right">{row.protein}</StyledTableCell>
                <StyledTableCell align="right">
                    <Button><Delete/></Button>
                </StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
};

export default coupon;
