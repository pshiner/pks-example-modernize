import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Button,
  TextField,
  MenuItem,
  Box,
  Paper,
  Typography, 
  IconButton,
} from "@mui/material";
import { useForm,Controller } from "react-hook-form";
import { useDropdown } from "../context/dropDownContext"; // Import the dropdown hook
import { useEffect,useState,React } from "react";
import axiosInstance from "../api/axiosInstance";
import {useSnackbarContext} from "../context/SnackbarContext";
import { useInterestRate } from "../context/IntrestRateContext";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import ExpandLessIcon from "@mui/icons-material/ExpandLess";
import CustomDataGridToolbar from "../components/common/CustomDataGridToolBar"
import { DataGrid } from '@mui/x-data-grid';
import { getColumns } from "./LoanTerm/PaymentTableColumns";


export default function CreateEditLoan({ open, handleClose, onSuccess,id}) {
  
  {/** No need to handle form manually React-hook-form will handle but keeping it in case 
  const [formData, setFormData] = useState({
    id: id,
    name: "",
    description: "",
    rate: "",
    amount: "",
    fundsDisbursementDate: "",
    firstStatementDate: "",
    firstInterestPaymentDate: "",
    firstPrincipalPaymentDate: "",
    currentMaturityDate: "",
    finalMaturityDate: "",
    loanMethodType: "",
    loanPeriodType: "",
    paymens: null
  });
*/}


const { setSnackbarMessage, refreshTable } = useSnackbarContext(); // ✅ Use global Snackbar and refresh function

const { getDropdownOptions } = useDropdown(); 
const { interestRate} = useInterestRate();

// Retrieve dropdown data dynamically
const methodOptions = getDropdownOptions("methods");
const periodOptions = getDropdownOptions("periods");
const columns = getColumns();
const [expanded, setExpanded] = useState(false); //expand payments section state
const [loading,setLoading] = useState(true)


  useEffect(() => {
   
    if (!open===true) return; 

    reset(); //reset form values
    setExpanded(false); //make sure payment seection is not enabl initially

    if (!id) { //new mode
      setLoading(false);
      //For new loans use predifined loan from api call
      setValue("rate", interestRate); 
      return;
    }
    //edit mode
    axiosInstance
      .get(`/loan/${id}`)
      .then((response) => {
        reset(response.data)
        //setFormData(response.data);
        setLoading(false);
      })
      .catch((err) => {
        alert("Error: " + err.message);
        setLoading(false);
      });
  }, [open, id]);

  
  
  const {
    register,
    handleSubmit,
    formState: { errors },
    control,
    setValue,
    getValues,
    reset
  } = useForm({ mode: "onTouched" });




/* React hook form will do this for us
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value
    }));
  };
*/


  const handleFormSubmit = async (data) => {
    try {
      if (id) {//edit mode
        await axiosInstance.put(`/loan/${id}`, data); //await to return
        setSnackbarMessage(`Loan updated successfully!`); //     
      } else { //new mode
        await axiosInstance.post("/loan/", data);
        setSnackbarMessage(`Loan created successfully!`); // 
      }

      refreshTable(); //refresh loan table
      handleClose(); //close dialog
      reset(); //reset form data       
   
    } catch (error) {
      console.log("Error submitting loan:", error.response?.data || error.message);
      alert(`Failed to submit loan.` , error) ;
    }  
  };

  return (
    <Dialog open={open} onClose={handleClose} fullWidth maxWidth="xl">
 
      <DialogTitle>{ id ? `Edit Loan ${id}` : "Add New Loan"} </DialogTitle>
      <form id="Loan" onSubmit={handleSubmit(handleFormSubmit)}>
        <DialogContent>
          <Box sx={{ display: "flex", gap: 3, flexDirection: { xs: "column", md: "row" } }}>
            <Paper elevation={3} sx={{ flex: 1, padding: 3, borderRadius: 2 }}>
              <TextField
                id="loan-name"
                required
                fullWidth
                label="Loan Name"
                {...register("name", { required: "Loan name is required" })} 
                error={!!errors.name}
                helperText={errors.name?.message}
              />
              <TextField
                id="loan-description"
                label="Loan Description"
                required
                fullWidth
                sx={{ mt: 2 }}
                {...register("description", { required: "Loan description is required" })}
                error={!!errors.description}
                helperText={errors.description?.message}
                multiline
                minRows={2}
                maxRows={4}
              />
              <TextField
                id="loan-rate"
                required
                label="Loan Rate"
                type="number"
                {...register("rate", { required: "Loan rate is required" })}
                error={!!errors.rate}
                helperText={errors.rate?.message}
                sx={{ mt: 2 }}
                slotProps={{
                  input: {
                    readOnly: true,
                  },
                  inputLabel: {
                    shrink: true,
                  },
                }}
              />
              <TextField
                id="loan-amount"
                label="Loan Amount"
                type="String"
                required
                {...register("amount", { required: "Loan amount is required" })}
                error={!!errors.amount}
                helperText={errors.amount?.message}
                sx={{ mt: 2, ml: 2 }}
                slotProps={{
                  inputLabel: {
                    shrink: true,
                  },
                }}
              />

              {/**React Form Hook handles dropdowns diffrently */}
              <Controller
                name="loanMethodType"
                control={control}
                defaultValue=""
                rules={{ required: "Method Type is required" }}
                render={({ field }) => (
                  <TextField
                    {...field} //Binds value & onChange properly
                    id="load-method"
                    select
                    label="Method Type"
                    fullWidth
                    sx={{ mt: 2, ml: 2, width: "25%" }}
                    error={!!errors.loanMethodType}
                    helperText={errors.loanMethodType?.message}
                  >
                    {methodOptions.map((option) => (
                      <MenuItem key={option.code} value={option.code}>
                        {option.name}
                      </MenuItem>
                    ))}
                  </TextField>
                )}
              />


              <Controller
                name="loanPeriodType"
                control={control}
                defaultValue="" // 
                rules={{ required: "Period Type is required" }}
                render={({ field }) => (
                  <TextField
                    {...field}
                    id="loan-period"
                    select
                    label="Period Type"
                    fullWidth
                    sx={{ mt: 2, ml: 2, width: "25%" }}
                    error={!!errors.loanPeriodType}
                    helperText={errors.loanPeriodType?.message}
                  >
                    {periodOptions.map((option) => (
                      <MenuItem key={option.code} value={option.code}>
                        {option.name}
                      </MenuItem>
                    ))}
                  </TextField>
                )}
              />


             
            <TextField
              label="Disbursement Date"
              type="date"
              sx={{ mt: 2, width: "25%" }}
              {...register("fundsDisbursementDate", { required: "Disbursement date is required" })}
              error={!!errors.fundsDisbursementDate}
              helperText={errors.fundsDisbursementDate?.message}
              InputLabelProps={{ shrink: true }}
            />
            <TextField
              label="Statement Date"
              type="date"
              sx={{ mt: 2, ml: 2, width: "25%" }}
              {...register("firstStatementDate", { required: "Statement date is required" })}
              error={!!errors.firstStatementDate}
              helperText={errors.firstStatementDate?.message}
        
              InputLabelProps={{ shrink: true }}
            />
            <TextField
              label="Interest Date"
              type="date"
              sx={{ mt: 2, ml: 2, width: "25%" }}
              {...register("firstInterestPaymentDate", { required: "Interest date is required" })}
              error={!!errors.firstInterestPaymentDate}
              helperText={errors.firstInterestPaymentDate?.message}
              InputLabelProps={{ shrink: true }}
            />
            <TextField
              label="Principal Date"
              type="date"
              sx={{ mt: 2, width: "25%" }}
              {...register("firstPrincipalPaymentDate", { required: "Principal date is required" })}
              error={!!errors.firstPrincipalPaymentDate}
              helperText={errors.firstPrincipalPaymentDate?.message}
              InputLabelProps={{ shrink: true }}
            />
            <TextField
              label="Current Maturity Date"
              type="date"
              sx={{ mt: 2, ml: 2, width: "25%" }}
              {...register("currentMaturityDate", { required: "Current Maturity date is required" })}
              error={!!errors.currentMaturityDate}
              helperText={errors.currentMaturityDate?.message}
              InputLabelProps={{ shrink: true }}
            />
            <TextField
              label="Final Maturity Date"
              type="date"
              sx={{ mt: 2, ml: 2, width: "25%" }}
              {...register("finalMaturityDate", { required: "Final Maturity is required" })}
              error={!!errors.finalMaturityDate}
              helperText={errors.finalMaturityDate?.message}
              InputLabelProps={{ shrink: true }}
            />
              {/* <TextField
              label="Purchase Date"
              type="date"
              sx={{
                  mt: 2,
                  ml: 2,
                  width: "25%",
                  backgroundColor: "#f0f0f0",  // Light gray background
                  opacity: 0.7,                // Dimmed effect
                  pointerEvents: "none",       // Prevents interaction
                  borderRadius: "4px",
                }}
              {...register("purchaseDate", { required: "Start date is required" })}
              error={!!errors.startDate}
              helperText={errors.startDate ? errors.startDate.message : ""}
              InputLabelProps={{ shrink: true }}
              InputProps={{ readOnly: true }}
            /> */}
            </Paper>
          </Box>

          {/* START Payment Box */}

          {/* Header - Clickable to Expand/Collapse */}
          <Box
            sx={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              cursor: "pointer",
              pt: 5
            }}
            onClick={() => setExpanded(!expanded)}
          >
            <Typography variant="h6">Payment Schedule</Typography>
            <IconButton size="small">
              {expanded ? <ExpandLessIcon /> : <ExpandMoreIcon />}
            </IconButton>
          </Box>

          {/* Expandable Content Inside Paper */}
          {expanded && (
            <Box
              sx={{
                mt: 2,
                p: 2,
                borderRadius: 2,
              }}
            >
              <Paper elevation={3} sx={{ flex: 1, padding: 3, borderRadius: 2 }}>
                {// Check If New Loan
                id ?
                <DataGrid
                  rows={getValues("payments")}
                  getRowId={(row) => row.date || Math.random().toString(36).substring(2, 9)} // Settinng custom ID since API doesn't return one
                  columns={columns}
                  initialState={{
                    columns: getValues("payments"),
                    pagination: {
                      paginationModel: {
                        pageSize: 5,
                      },
                    },
                    density: "compact"
                  }}
                  pageSizeOptions={[15]}
                  checkboxSelection
                  disableRowSelectionOnClick
                  slots={{ toolbar: CustomDataGridToolbar }}
                />
                : "Payment schedule will appear once loan is created/priced"}
              </Paper>
            </Box>
          )}
        {/* END Payment Box */}
        </DialogContent>
      </form>
      <DialogActions>
        <Button onClick={handleClose} color="secondary">
          Cancel
        </Button>
        <Button type="submit" form="Loan" variant="contained" color="primary">
          Submit
        </Button>
      </DialogActions>
    </Dialog>
  );
}
