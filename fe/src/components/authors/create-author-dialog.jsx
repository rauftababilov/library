import { useMutation } from 'react-query';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';

import { createAuthor } from 'api/authors';

export function CreateAuthorDialog({ open, onClose, onSuccess }) {
  const createBookMutation = useMutation(createAuthor, {
    onSuccess: () => {
      onClose();
      onSuccess();
    },
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);

    createBookMutation.mutate({
      fullName: formData.get('fullName'),
    });
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Create Author</DialogTitle>
      <DialogContent>
        <Container maxWidth="sm">
          <Box component="form" onSubmit={handleSubmit}>
            <Grid container rowSpacing={3}>
              <Grid item xs={12}>
                <TextField
                  id="fullName"
                  label="Full Name"
                  name="fullName"
                  margin="normal"
                  required
                  fullWidth
                  autoComplete="off"
                  autoFocus
                  size="small"
                />
              </Grid>
              <Grid item xs={12}>
                <Box sx={{ display: 'flex', gap: '20px' }}>
                  <Button type="submit" variant="contained" color="success" disabled={createBookMutation.isLoading}>
                    Create
                  </Button>
                  <Button type="button" variant="contained" color="error" onClick={onClose}>
                    Cancel
                  </Button>
                </Box>
              </Grid>
            </Grid>
          </Box>
        </Container>
      </DialogContent>
    </Dialog>
  );
}
