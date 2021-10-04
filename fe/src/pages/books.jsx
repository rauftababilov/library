import { useState } from 'react';
import { useQueryClient } from 'react-query';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';

import { BooksTable, CreateBookDialog } from 'components/books';

const DIALOGS = {
  CREATE_BOOK: 'CREATE_BOOK',
};

function BooksPage() {
  const [openDialog, setOpenDialog] = useState(null);
  const queryClient = useQueryClient();

  const handleDialogOpen = (dialogName) => {
    if (DIALOGS[dialogName]) {
      setOpenDialog(dialogName);
    }
  };

  const handleBookCreated = () => {
    queryClient.invalidateQueries('books');
  };

  const handleDialogClose = () => {
    setOpenDialog(null);
  };

  return (
    <Container maxWidth="xl" sx={{ paddingTop: '24px', paddingBottom: '50px' }}>
      <Paper>
        <Grid container>
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
            <BooksTable />
          </Grid>
        </Grid>
      </Paper>
      <CreateBookDialog
        open={openDialog === DIALOGS.CREATE_BOOK}
        onClose={handleDialogClose}
        onSuccess={handleBookCreated}
      />
    </Container>
  );
}

export default BooksPage;
