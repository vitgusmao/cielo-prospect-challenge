import { useState } from 'react';
import { FaPlus } from 'react-icons/fa';

import ContentHolder from '../../../../components/ContentHolder';
import Divider from '../../../../facades/Divider';
import Radio from '../../../../facades/Radio';

import LegalEntityForm from './LegalEntityForm';
import NaturalPersonForm from './NaturalPersonForm';

function NewClient() {
  const [clientType, setClientType] = useState<'naturalPerson' | 'legalEntity'>(
    'naturalPerson'
  );

  return (
    <ContentHolder label="Adicionar cliente" level={2} iconComponent={FaPlus}>
      <Radio.Group
        value={clientType}
        onChange={({ target: { value } }) => {
          setClientType(value);
        }}
      >
        <Radio value="naturalPerson">Pessoa física</Radio>
        <Radio value="legalEntity">Pessoa jurídica</Radio>
      </Radio.Group>
      <Divider />
      {clientType === 'naturalPerson' ? (
        <NaturalPersonForm />
      ) : (
        <LegalEntityForm />
      )}
    </ContentHolder>
  );
}

export default NewClient;
