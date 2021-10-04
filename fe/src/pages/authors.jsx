import { useState } from 'react';
import { useQueryClient } from 'react-query';
import Container from '@mui/material/Container';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';

import { AuthorsTable, CreateAuthorDialog } from 'components/authors';

const DIALOGS = {
  CREATE_AUTHOR: 'CREATE_AUTHOR',
};

function AuthorsPage() {
  const queryClient = useQueryClient();
  const [openDialog, setOpenDialog] = useState(null);

  const handleDialogOpen = (dialogName) => {
    if (DIALOGS[dialogName]) {
      setOpenDialog(dialogName);
    }
  };

  const handleDialogClose = () => {
    setOpenDialog(null);
  };

  const handleAuthorCreationSuccess = () => {
    queryClient.invalidateQueries('authors');
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
                onClick={() => handleDialogOpen(DIALOGS.CREATE_AUTHOR)}
              >
                Create
              </Button>
            </Box>
          </Grid>
          <Grid item xs={12}>
            <AuthorsTable />
          </Grid>
        </Grid>
      </Paper>
      <CreateAuthorDialog
        open={openDialog === DIALOGS.CREATE_AUTHOR}
        onClose={handleDialogClose}
        onSuccess={handleAuthorCreationSuccess}
      />
    </Container>
  );
}

export default AuthorsPage;
