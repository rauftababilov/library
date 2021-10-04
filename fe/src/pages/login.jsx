import { useHistory } from 'react-router';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';

import { routesURLs } from 'common/constants';

function LoginForm() {
  const history = useHistory();

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);

    console.log({
      username: data.get('username'),
      password: data.get('password'),
    });

    // TODO: temporary logic
    history.push(routesURLs.books);
  };

  return (
    <Container component="main" maxWidth="xs">
      <Box
        sx={{
          marginTop: 8,
          display: 'flex',
          flexDirection: 'column',
        }}
      >
        <Typography component="h1" variant="h3">
          Login
        </Typography>
        <Box component="form" onSubmit={handleSubmit} sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="username"
            label="Username"
            name="username"
            autoComplete="username"
            autoFocus
            size="small"
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
            size="small"
          />
          <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2, alignSelf: 'center' }}>
            Login
          </Button>
        </Box>
      </Box>
    </Container>
  );
}

function LoginPage() {
  return <LoginForm />;
}

export default LoginPage;
