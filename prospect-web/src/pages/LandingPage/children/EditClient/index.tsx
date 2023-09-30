import { FaEdit } from 'react-icons/fa';
import { useParams } from 'react-router-dom';

import { ClientType } from '../../../../@types';
import ContentHolder from '../../../../components/ContentHolder';
import { getApiEndpoint } from '../../../../data';
import useHttpGet from '../../../../hooks/useHttpGet';
import { isLegalEntity } from '../../../../utils';

import LegalEntityForm from './LegalEntityForm';
import NaturalPersonForm from './NaturalPersonForm';

function EditClient() {
  const { id } = useParams();

  const client = useHttpGet<ClientType>(getApiEndpoint(`/clients/${id}`));

  if (!client) {
    return null;
  }

  return (
    <ContentHolder label="Adicionar cliente" level={2} iconComponent={FaEdit}>
      {isLegalEntity(client) ? (
        <LegalEntityForm client={client} />
      ) : (
        <NaturalPersonForm client={client} />
      )}
    </ContentHolder>
  );
}

export default EditClient;
