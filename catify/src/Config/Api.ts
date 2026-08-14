import axios from 'axios';

export const API_URL = "https://cartify-backend-fecpevb0fvadhzcb.eastasia-01.azurewebsites.net/";
export const DEPLOYED_URL = ""
// change api

export const api = axios.create({
  baseURL: API_URL, 
  headers: {
    'Content-Type': 'application/json',
  },
});