export const getColumns = () => {
    return [
        {
          field: 'date', 
          headerName: 'Date',
          flex: 0.2,
          type: 'date', 
          valueGetter: (params) => {
            console.log(params)
            // Transform the string date to a Date object (assuming it's a string in ISO format)
            return new Date(params);
          }
        },
        {
          field: 'amount',
          headerName: 'Amount',
          type: 'number',
          flex: 0.2,
          editable: false,
          headerAlign: 'left', //nubers are alligned to right by default
          align: 'left', 
          valueFormatter: (params) => `$${params}`, //Add the $ symbol
        },
        {
          field: 'interest',
          headerName: 'Interest',
          type: 'number',
          flex: 0.2,
          editable: false,
          headerAlign: 'left', //nubers are alligned to right by default
          align: 'left', 
          valueFormatter: (params) => `$${params}`, //Add the $ symbol
        },
        {
          field: 'principal',
          headerName: 'Principal',
          type: 'number',
          flex: 0.2,
          editable: false,
          headerAlign: 'left', //nubers are alligned to right by default
          align: 'left', 
          valueFormatter: (params) => `$${params}`, //Add the $ symbol
        },
      ]
}
