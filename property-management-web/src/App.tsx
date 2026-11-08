import { Box, Paper, Typography } from '@mui/material';

const App = () => {
  return (
    <Box sx={{ p: 4 }}>
      <Paper variant='outlined' sx={{ p: 3 }}>
        <Typography variant='h5' sx={{ fontWeight: 600 }} gutterBottom>
          Property Management
        </Typography>
        <Typography color='text.secondary'>
          Manage your properties and farming seasons
        </Typography>
      </Paper>
    </Box>
  );
};

export default App;
