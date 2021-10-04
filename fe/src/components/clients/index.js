import { useState } from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';

import { PublishersTable } from './clients-table';
import { CreatePublisherDialog } from './create-clients-dialog';


const DIALOGS = {
  CREATE_BOOK: 'CREATE_BOOK',
};

function TablesWrapper() {
  const [openDialog, setOpenDialog] = useState(null);


  const handleDialogOpen = (dialogName) => {
    if (DIALOGS[dialogName]) {
      setOpenDialog(dialogName);
    }
  };

  const handleDialogClose = () => {
    setOpenDialog(null);
  };

  return (
    <Container maxWidth="xl" sx={{ paddingTop: '24px', paddingBottom: '50px' }}>
      <Paper>
          <Grid role="tabpanel">
            <Grid item xs={6}>
              <Box sx={{ padding: '20px' }}>
                <Button
                  type="button"
                  color="success"
                  variant="contained"
                  endIcon={<AddCircleOutlineIcon />}
                  onClick={() => handleDialogOpen(DIALOGS.CREATE_BOOK)}
                >
                  Create
                </Button>
              </Box>
            </Grid>
            <Grid item xs={12}>
              <PublishersTable />
            </Grid>
          </Grid>
      </Paper>
      <CreatePublisherDialog open={openDialog === DIALOGS.CREATE_BOOK} onClose={handleDialogClose} />
    </Container>
  );
}

export default TablesWrapper;
