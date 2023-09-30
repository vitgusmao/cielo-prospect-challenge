import { useState } from 'react';

import Input from '../facades/Input';

function InputCPF({ value, onChange }: Parameters<typeof Input>[0]) {
  const [validValue, setValidValue] = useState(value);

  const middlewareOnChange: typeof onChange = (event) => {
    const newValue = event.target.value;
    if (
      newValue.length <= 4 &&
      (!Number.isNaN(+newValue) || newValue.length === 0)
    ) {
      setValidValue(newValue);
      onChange?.(event);
    }
  };

  return <Input value={validValue} onChange={middlewareOnChange} />;
}

export default InputCPF;
