import { useQuery } from 'react-query';
import { useState } from 'react';
import TableCell from '@mui/material/TableCell';
import TableRow from '@mui/material/TableRow';

import { getAllPublishers, removePublisher } from 'api/publishers';
import { DataTable } from 'components/ui/data-table';
import { DataTableActionsCell } from 'components/ui/data-table';
import { EditPublisherDialog } from './edit-publisher-dialog'; 

const bookTableHeadCells = [
  {
    id: 'title',
    disablePadding: true,
    label: 'Title',
  },
];

export function PublishersTable() {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const [{openEdit, ...valueForm}, setEditDialog] = useState({openEdit: false, title: '',});

  const { data, isLoading, isError, error, remove } = useQuery(['publisher', page, rowsPerPage], () =>
    getAllPublishers({ page, size: rowsPerPage })
  );

  const handleChangePage = (_, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const onRemove = ( id ) => {
    removePublisher({id});
    remove(id);
  }

  const handleDialogClose = () => {
    setEditDialog({openEdit: false, title: ''});
  }


  const onEdit = (publisher) => {
    console.log('publisher', publisher);
    setEditDialog({openEdit: true, ...publisher})
  }
  console.log('valueForm', valueForm)
  return (
    <>
      <DataTable
        cells={bookTableHeadCells}
        rows={data?.content ?? []}
        isLoading={isLoading}
        error={isError ? error : null}
        totalElements={data?.totalElements}
        page={page}
        rowsPerPage={rowsPerPage}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
        renderRow={(publisher) => (
          <TableRow hover key={publisher.id}>
            <TableCell component="th" scope="row" padding="none" align="left">
              {publisher.title}
            </TableCell>
            <DataTableActionsCell onEdit={() => onEdit(publisher)} onDelete={() => onRemove(publisher.id)} />
          </TableRow>
        )}
      />
      <EditPublisherDialog open={openEdit} onClose={handleDialogClose} valueForm={valueForm} />
    </>
  );
}
