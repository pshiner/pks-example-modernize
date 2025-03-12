import * as React from 'react';
import Box from '@mui/material/Box';
import { DataGrid } from '@mui/x-data-grid';
import axiosInstance from '../../api/axiosInstance';
import { useEffect } from 'react';
import { useState } from 'react';
import { getColumns } from './LoanTableColumns';
import CustomDataGridToolbar from '../common/CustomDataGridToolBar'
import { useSnackbarContext } from "../../context/SnackbarContext"; // ✅ Import Context Hook

export default function DataGridDemo() {
  const [rows, setRows] = useState([]);
  const [loading, setLoading] = useState(true);
  const { columns, columnVisibilityModel } = getColumns();
  const { refreshTrigger } = useSnackbarContext();


 
  useEffect(() => {
    axiosInstance
      .get('/loan/')  
      .then((response) => {
        setRows(response.data);  
        setLoading(false); 
      })
      .catch((err) => {
        alert("Error: " + err.message);
        setLoading(false); 
      });
  }, [refreshTrigger]);

  return (
    <Box sx={{ flex: 1}}>
      <DataGrid
        rows={rows}
        columns={columns}
        initialState={{
          columns : {columnVisibilityModel},
          pagination: {
            paginationModel: {
              pageSize: 5,
            },
          },
        }}
        pageSizeOptions={[15]}
        loading={loading}  //// loading functionality spinning icon while waiting for data
        checkboxSelection
        disableRowSelectionOnClick
        slots={{ toolbar: CustomDataGridToolbar}} 
      />
    </Box> 
  );
}
