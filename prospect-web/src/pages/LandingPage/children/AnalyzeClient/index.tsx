import { useEffect, useRef, useState } from 'react';
import { FaPlus } from 'react-icons/fa';
import { Link } from 'react-router-dom';

import ContentHolder from '../../../../components/ContentHolder';
import { getApiEndpoint } from '../../../../data';
import Button from '../../../../facades/Button';
import Descriptions from '../../../../facades/Descriptions';
import Empty from '../../../../facades/Empty';
import useHttpPost from '../../../../hooks/useHttpPost';

import AnalyzeActions from './AnalyzeActions';

const labelMappings: Record<string, string> = {
  cpf: 'CPF',
  name: 'Nome',
  email: 'E-mail',
  cnpj: 'CNPJ',
  corporateReason: 'Razão social',
  contactCpf: 'CPF',
  contactEmail: 'E-mail',
  contactName: 'Nome',
  mcc: 'MCC',
  // createdOn:'Criado em',
  // updatedOn: 'Atualizado em',
};

function AnalyzeClient() {
  const [prospect, setProspect] = useState<unknown>();
  const [emptyQueue, setEmptyQueue] = useState<boolean>(false);
  const post = useHttpPost(getApiEndpoint('/prospects'), {
    expectedStatus: 200,
  });
  const requested = useRef(false);

  useEffect(() => {
    if (!requested.current) {
      post().then((body) => {
        if (body) {
          setProspect(body);
        } else {
          setEmptyQueue(true);
        }
      });
      requested.current = true;
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <ContentHolder label="Análise de cliente" level={2} iconComponent={FaPlus}>
      {/* eslint-disable-next-line no-nested-ternary */}
      {emptyQueue ? (
        <Empty description="lista vazia">
          <Button
            onClick={(e) => {
              e.preventDefault();
            }}
          >
            <Link to="/clients">Voltar para a lista</Link>
          </Button>
        </Empty>
      ) : isProspectValid(prospect) ? (
        <>
          <Descriptions>
            {Object.entries(labelMappings).map(([key, label]) => {
              if (key in prospect) {
                return (
                  <Descriptions.Item key={key} label={label}>
                    {String(prospect[key])}
                  </Descriptions.Item>
                );
              }
              return null;
            })}
          </Descriptions>
          <AnalyzeActions id={parseInt(prospect.id, 10)} />
        </>
      ) : null}
    </ContentHolder>
  );
}

function isProspectValid(
  prospect: unknown
): prospect is Record<string, string> {
  return (
    !!prospect &&
    prospect !== null &&
    typeof prospect === 'object' &&
    'id' in prospect
  );
}

export default AnalyzeClient;
