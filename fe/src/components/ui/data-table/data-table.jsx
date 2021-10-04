import Box from '@mui/material/Box';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TablePagination from '@mui/material/TablePagination';
import CircularProgress from '@mui/material/CircularProgress';

import { TABLE_ROWS_PER_PAGE_OPTIONS } from 'common/constants';

import { DataTableHead } from './data-table-head';

const DEFAUL_TABLE_ROW_HEIHGT = 53;

function LoadingTableBody({ rowsCount, cellsCount }) {
  const rowHeight = `${DEFAUL_TABLE_ROW_HEIHGT * rowsCount}px`;

  return (
    <TableRow sx={{ height: rowHeight }}>
      <TableCell colSpan={cellsCount}>
        <Box
          sx={{
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
          }}
        >
          <CircularProgress />
        </Box>
      </TableCell>
    </TableRow>
  );
}

export function DataTable({
  cells,
  rows,
  renderRow,
  totalElements,
  onPageChange,
  onRowsPerPageChange,
  isLoading = false,
  page,
  rowsPerPage,
  rowsPerPageOptions = TABLE_ROWS_PER_PAGE_OPTIONS,
}) {
  return (
    <Box sx={{ padding: '0 20px' }}>
      <TableContainer>
        <Table aria-labelledby="tableTitle">
          <DataTableHead cells={cells} />
          <TableBody>
            {isLoading ? (
              <LoadingTableBody rowsCount={rowsPerPage} cellsCount={cells.length} />
            ) : (
              rows?.map((rowData) => renderRow(rowData))
            )}
          </TableBody>
        </Table>
      </TableContainer>

      <TablePagination
        rowsPerPageOptions={rowsPerPageOptions}
        component="div"
        count={isLoading ? -1 : totalElements}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={onPageChange}
        onRowsPerPageChange={onRowsPerPageChange}
      />
    </Box>
  );
}
