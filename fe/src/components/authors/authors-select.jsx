import { useState } from 'react';
import { useQuery } from 'react-query';
import { getAllAuthors } from 'api/authors';
import { AsyncSelect } from 'components/ui/async-select';

export function AuthorsSelect({ onChange, size = 'normal', multiple = false, value }) {
  const [open, setOpen] = useState(false);
  const { data, isLoading } = useQuery('authors', getAllAuthors, { enabled: open });

  return (
    <AsyncSelect
      label="Authors"
      id="authors"
      options={data?.content}
      isLoading={isLoading}
      isOpen={open}
      onOpen={() => setOpen(true)}
      onClose={() => setOpen(false)}
      onChange={onChange}
      multiple={multiple}
      size={size}
      getOptionLabel={(option) => option.fullName}
      isOptionEqualToValue={(option, value) => option.fullName?.includes(value?.fullName)}
      value={value}
    />
  );
}
