import { useState } from 'react';
import TableCell from '@mui/material/TableCell';
import IconButton from '@mui/material/IconButton';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';

import MoreVertIcon from '@mui/icons-material/MoreVert';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

export function DataTableActionsCell({ onDelete, onEdit, align = 'right', edit = true }) {
  const [anchor, setAnchor] = useState(null);

  const handleMenuOpen = (e) => {
    setAnchor(e.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchor(null);
  };

  const handleDelete = () => {
    setAnchor(null);
    onDelete();
  };

  const handleEdit = () => {
    setAnchor(null);
    onEdit();
  };

  return (
    <TableCell align={align}>
      <IconButton onClick={handleMenuOpen}>
        <MoreVertIcon />
      </IconButton>
      <Menu anchorEl={anchor} open={Boolean(anchor)} onClose={handleMenuClose}>
        {edit && (
        <MenuItem onClick={handleEdit}>
          <ListItemIcon>
            <EditIcon fontSize="small" />
          </ListItemIcon>
          <ListItemText>Edit</ListItemText>
        </MenuItem>
        )}
        <MenuItem onClick={handleDelete}>
          <ListItemIcon>
            <DeleteIcon fontSize="small" />
          </ListItemIcon>
          <ListItemText>Delete</ListItemText>
        </MenuItem>
      </Menu>
    </TableCell>
  );
}
