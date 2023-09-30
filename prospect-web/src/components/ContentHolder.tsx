import React from 'react';
import styled from 'styled-components';

import colors from '../colors';
import Card from '../facades/Card';

import Title from './Title';

const StyledDiv = styled.div`
  flex: 1;

  display: flex;
  flex-direction: column;

  .header {
    display: flex;
    align-items: center;
    column-gap: 8px;
    margin-bottom: 10px;
  }
  .content-title {
    padding-left: 12px;
    margin: 0;
  }

  .content-holder {
    border-radius: 18px;

    background-color: ${colors.background};

    margin-bottom: 20px;
  }
`;

function ContentHolder({
  label,
  level,
  extra = undefined,
  iconComponent = undefined,
  children = null,
}: {
  label: string;
  extra?: React.ReactNode;
  children?: React.ReactNode;
} & Parameters<typeof Title>[0]) {
  return (
    <StyledDiv
      aria-label={`container de informações de ${label}`}
      role="contentinfo"
    >
      <div
        className="header"
        aria-label="cabeçalho do container"
        role="textbox"
      >
        <Title
          label={label}
          level={level}
          iconComponent={iconComponent}
          className="content-title"
          style={{ color: colors.secondaryTitleColor }}
        />
        {extra || null}
      </div>
      <Card
        className="content-holder"
        aria-label={`container de informações de ${label}`}
        role="contentinfo"
      >
        {children}
      </Card>
    </StyledDiv>
  );
}

export default ContentHolder;
