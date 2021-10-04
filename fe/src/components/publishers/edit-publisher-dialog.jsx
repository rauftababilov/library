import { useState, useEffect } from 'react';
import { useMutation, useQueryClient } from 'react-query';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import { editPublisher } from 'api/publishers';

export function EditPublisherDialog({ open, onClose, valueForm, }) {
  const [bookTitle, setBookTitle] = useState(valueForm.title || "");
  const queryClient = useQueryClient();
  const createBookMutation = useMutation((newBook) => editPublisher(newBook), {
    onSuccess: () => {
      onClose();
      queryClient.invalidateQueries('publisher');
    }
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    createBookMutation.mutate({
      id: valueForm.id,
      title: bookTitle,
    });
  };

  const handleTitleChange = (e) => {
    setBookTitle(e.target.value);
  };

  useEffect(() => {
    console.log('value', valueForm);
    setBookTitle(valueForm.title);
  }, [valueForm]);

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Edit Publisher</DialogTitle>
      <DialogContent>
        <Container maxWidth="sm">
          <Box component="form" onSubmit={handleSubmit}>
            <Grid container rowSpacing={3}>
              <Grid item xs={12}>
                <TextField
                  id="title"
                  label="Title"
                  name="title"
                  margin="normal"
                  required
                  fullWidth
                  autoComplete="off"
                  autoFocus
                  size="small"
                  value={bookTitle}
                  onChange={handleTitleChange}
                />
              </Grid>
              <Grid item xs={12}>
                <Box sx={{ display: 'flex', gap: '20px', paddingTop: '20px' }}>
                  <Button type="submit" variant="contained" color="success" disabled={createBookMutation.isLoading}>
                    Update
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
