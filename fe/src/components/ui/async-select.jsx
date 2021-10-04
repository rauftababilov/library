import { useState, useEffect } from 'react';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import CircularProgress from '@mui/material/CircularProgress';

export function AsyncSelect({
  isOpen,
  onChange,
  onOpen,
  onClose,
  isLoading = false,
  options,
  isOptionEqualToValue,
  size = 'normal',
  multiple = false,
  id = '',
  getOptionLabel,
  label,
  value,
}) {
  const [_options, setOptions] = useState([]);

  useEffect(() => {
    if (!isLoading && Array.isArray(options)) {
      setOptions(options);
    }
  }, [isLoading, options]);

  return (
    <Autocomplete
      id={id}
      multiple={multiple}
      options={_options}
      loading={isLoading}
      getOptionLabel={getOptionLabel}
      isOptionEqualToValue={isOptionEqualToValue}
      open={isOpen}
      onOpen={onOpen}
      onClose={onClose}
      onChange={onChange}
      value={value}
      renderInput={(params) => (
        <TextField
          {...params}
          label={label}
          size={size}
          InputProps={{
            ...params.InputProps,
            endAdornment: (
              <>
                {isLoading ? <CircularProgress color="inherit" size={20} /> : null}
                {params.InputProps.endAdornment}
              </>
            ),
          }}
        />
      )}
    />
  );
}
