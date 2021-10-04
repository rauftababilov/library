import { useState } from 'react';
import { useMutation } from 'react-query';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';

import { AuthorsSelect } from 'components/authors/';
import { PublishersSelect } from 'components/publishers/publishers-select';
import { createBook } from 'api/books';

export function CreateBookDialog({ open, onClose, onSuccess }) {
  const [bookAuthors, setBookAuthors] = useState([]);
  const [bookPublisher, setBookPublisher] = useState(null);

  const createBookMutation = useMutation((newBook) => createBook(newBook), {
    onSuccess: () => {
      onClose();
      onSuccess();
    },
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);

    createBookMutation.mutate({
      title: formData.get('title'),
      authorIds: bookAuthors.map((author) => author.id),
      publishingHouseId: bookPublisher.id,
      publishYear: formData.get('publishYear'),
    });
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Create Book</DialogTitle>
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
                />
              </Grid>
              <Grid item xs={12}>
                <AuthorsSelect size="small" multiple onChange={(_, value) => setBookAuthors(value)} />
              </Grid>
              <Grid item xs={12}>
                <PublishersSelect size="small" onChange={(_, value) => setBookPublisher(value)} />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  id="publishYear"
                  name="publishYear"
                  label="Publication Year"
                  type="number"
                  size="small"
                  fullWidth
                  required
                />
              </Grid>
              <Grid item xs={12}>
                <Box sx={{ display: 'flex', gap: '20px', paddingTop: '20px' }}>
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
