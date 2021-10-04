import { useState } from 'react';
import { useQuery } from 'react-query';
import { AsyncSelect } from 'components/ui/async-select';
import { getAllPublishers } from 'api/publishers';

export function PublishersSelect({ onChange, size = 'normal', multiple = false }) {
  const [open, setOpen] = useState(false);
  const { data, isLoading } = useQuery('publishers', getAllPublishers, { enabled: open });

  return (
    <AsyncSelect
      label="Publishers"
      id="publishers"
      options={data?.content}
      isLoading={isLoading}
      isOpen={open}
      onOpen={() => setOpen(true)}
      onClose={() => setOpen(false)}
      onChange={onChange}
      multiple={multiple}
      size={size}
      getOptionLabel={(option) => option.title}
      isOptionEqualToValue={(option, value) => option.title?.includes(value?.title)}
    />
  );
}
