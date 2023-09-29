import React from 'react';
import styled from 'styled-components';

import colors from '../colors';
import Card from '../facades/Card';

import Title from './Title';

const StyledDiv = styled.div`
  flex: 1;

  display: flex;
  flex-direction: column;

  .content-title {
    padding-left: 12px;
  }

  .content-holder {
    border-radius: 18px;

    background-color: ${colors.background};
  }
`;

function ContentHolder({
  label,
  level,
  iconComponent = undefined,
  children = null,
}: {
  label: string;
  children?: React.ReactNode;
} & Parameters<typeof Title>[0]) {
  return (
    <StyledDiv
      aria-label={`container de informações de ${label}`}
      role="contentinfo"
    >
      <Title
        label={label}
        level={level}
        iconComponent={iconComponent}
        className="content-title"
        style={{ color: colors.secondaryTitleColor }}
      />
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
